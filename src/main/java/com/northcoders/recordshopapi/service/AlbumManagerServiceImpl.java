package com.northcoders.recordshopapi.service;

import com.northcoders.recordshopapi.model.Album;
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
    public Optional<Album> getAlbumById(Long albumId) {
        return albumManagerRepository.findById(albumId);
    }

    @Override
    public Album updateAlbumById(Optional<Album> albumToUpdate, Album albumFromUrl) {
        albumToUpdate.get().setAlbumName(albumFromUrl.getAlbumName());
        albumToUpdate.get().setArtist(albumFromUrl.getArtist());
        albumToUpdate.get().setYearReleased(albumFromUrl.getYearReleased());
        albumToUpdate.get().setGenre(albumFromUrl.getGenre());
        albumToUpdate.get().getStockId().setQuantityInStock(albumFromUrl.getStockId().getStockId());

        return albumManagerRepository.save(albumToUpdate.get());
    }
}
