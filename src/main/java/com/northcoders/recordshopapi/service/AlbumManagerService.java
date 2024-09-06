package com.northcoders.recordshopapi.service;

import com.northcoders.recordshopapi.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumManagerService {
    List<Album> getAllAlbums();

    Album insertAlbum(Album album);

    Album getAlbumById(Long albumId);

    Album updateAlbumById(Album albumToUpdate, Album albumFromUrl);

    void deleteAlbumById(Long albumId);

    List<Album> getAlbumsByGenre(String genre);

    List<Album> getAlbumsByYearReleased(Long yearReleased);
}
