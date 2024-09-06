package com.northcoders.recordshopapi.service;

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
        return albumManagerRepository.findById(albumId).get();
    }

    @Override
    public Album updateAlbumById(Album albumToUpdate, Album albumFromUrl) {
        albumToUpdate.setAlbumName(albumFromUrl.getAlbumName());
        albumToUpdate.setArtist(albumFromUrl.getArtist());
        albumToUpdate.setYearReleased(albumFromUrl.getYearReleased());
        albumToUpdate.setGenre(albumFromUrl.getGenre());
        albumToUpdate.setStockId(albumFromUrl.getStockId());
        //albumToUpdate.getStockId().setQuantityInStock(albumFromUrl.getStockId().getStockId());

        return albumManagerRepository.save(albumToUpdate);
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
}
