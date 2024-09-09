package com.northcoders.recordshopapi.service;

import com.northcoders.recordshopapi.exception.ItemNotFoundException;
import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.model.Genre;
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
    public Album insertAlbum(Album album) {
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
    public void deleteAlbumById(Long albumId) {
        albumManagerRepository.deleteById(albumId);
    }

    @Override
    public List<Album> getAlbumsByGenre(String genre) {
        Genre genreToSearch = Genre.parseGenre((genre));

        return albumManagerRepository.findAlbumsByGenre(genreToSearch);
    }

    @Override
    public List<Album> getAlbumsByYearReleased(Long yearReleased) {
        return albumManagerRepository.findAlbumsByYearReleased(yearReleased);
    }

    @Override
    public List<Album> getAlbumsByArtist(String artist) {
        return albumManagerRepository.findByArtistContainingIgnoreCase(artist);
    }

    @Override
    public List<Album> getAlbumByAlbumName(String albumName) {
        return albumManagerRepository.findByAlbumNameContainingIgnoreCase(albumName);
    }
}
