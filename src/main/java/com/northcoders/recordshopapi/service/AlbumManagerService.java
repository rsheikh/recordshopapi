package com.northcoders.recordshopapi.service;

import com.northcoders.recordshopapi.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumManagerService {
    List<Album> getAllAlbums();

    Album insertAlbum(Album album);

    Optional<Album> getAlbumById(Long albumId);

    Album updateAlbumById(Optional<Album> albumToUpdate, Album albumFromUrl);
}
