package com.northcoders.recordshopapi.service;

import com.northcoders.recordshopapi.exception.ItemNotFoundException;
import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.model.Genre;
import com.northcoders.recordshopapi.model.Stock;
import com.northcoders.recordshopapi.repository.AlbumManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumManagerServiceImpl implements AlbumManagerService {

    @Autowired
    AlbumManagerRepository albumManagerRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();
        albumManagerRepository.findAll().forEach(albums::add);

        return albums;
    }

    @Override
    public List<Album> getAllAlbumsInStock() {
        List<Album> albums = new ArrayList<>();

        albumManagerRepository.findAll().forEach(
                album -> {
                    if(album.getStockId().getQuantityInStock() > 0) {
                        albums.add(album);
                    }
                });

        if(!albums.isEmpty()) {
            return albums;
        } else {
            throw new ItemNotFoundException("There is no stock!");
        }
    }

    @Override
    public Album insertAlbum(Album album) {
        album.setStockId(new Stock(1L));
        return albumManagerRepository.save(album);
    }

    @Override
    public Album getAlbumById(Long albumId) {
        Optional<Album> album = albumManagerRepository.findById(albumId);
        if(album.isPresent()) {
            return album.get();
        } else {
            throw new ItemNotFoundException(String.format("Record with id '%s' cannot be found", albumId));
        }
    }

    @Override
    public Album updateAlbumById(Long albumId, Album albumFromUrl) {
        Optional<Album> album = albumManagerRepository.findById(albumId);

        Album albumToUpdate;
        if(album.isPresent()) {
            albumToUpdate = album.get();

            albumToUpdate.setAlbumName(albumFromUrl.getAlbumName());
            albumToUpdate.setArtist(albumFromUrl.getArtist());
            albumToUpdate.setGenre(albumFromUrl.getGenre());
            albumToUpdate = albumManagerRepository.save(albumToUpdate);
            return albumToUpdate;
        } else {
            throw new ItemNotFoundException(String.format("Album with id '%s' cannot be found to be updated", albumId));
        }
    }

    @Override
    public String deleteAlbumById(Long albumId) {
        Optional<Album> album = albumManagerRepository.findById(albumId);
        if(album.isPresent()) {
            albumManagerRepository.deleteById(albumId);
            return String.format("Album with id '%s' has been deleted successfully", albumId);
        } else {
            throw new ItemNotFoundException(String.format("Album with id '%s' cannot be found to be deleted", albumId));
        }
    }

    @Override
    public List<Album> getAlbumsByGenre(String genre) {
        Genre genreToSearch = Genre.parseGenre((genre));

        List<Album> albumList =  albumManagerRepository.findAlbumsByGenre(genreToSearch);
        if(!albumList.isEmpty()) {
            return albumList;
        } else {
            throw new ItemNotFoundException(String.format("No albums found that matched the genre '%s'", genre));
        }
    }

    @Override
    public List<Album> getAlbumsByYearReleased(Long yearReleased) {
        List<Album> albumList =  albumManagerRepository.findAlbumsByYearReleased(yearReleased);
        if(!albumList.isEmpty()) {
            return albumList;
        } else {
            throw new ItemNotFoundException(String.format("No albums found that were released in '%s'", yearReleased));
        }
    }

    @Override
    public List<Album> getAlbumsByArtist(String artist) {
        List<Album> albumList = albumManagerRepository.findByArtistContainingIgnoreCase(artist);
        if(!albumList.isEmpty()) {
            return albumList;
        } else {
            throw new ItemNotFoundException(String.format("No albums found that match artist '%s'", artist));
        }
    }

    @Override
    public List<Album> getAlbumByAlbumName(String albumName) {
        List<Album> albumList = albumManagerRepository.findByAlbumNameContainingIgnoreCase(albumName);
        if(!albumList.isEmpty()) {
            return albumList;
        } else {
            throw new ItemNotFoundException(String.format("No albums found that match album name of '%s'", albumName));
        }
    }
}
