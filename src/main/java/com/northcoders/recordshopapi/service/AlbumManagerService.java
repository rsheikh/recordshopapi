package com.northcoders.recordshopapi.service;

import com.northcoders.recordshopapi.model.Album;

import java.util.List;

public interface AlbumManagerService {
    List<Album> getAllAlbums();

    Album insertAlbum(Album album);

    Album getAlbumById(Long albumId);

//    Album updateAlbumById(Album albumToUpdate, Album albumFromUrl);
    Album updateAlbumById(Long albumId, Album albumFromUrl);

    String deleteAlbumById(Long albumId);

    List<Album> getAlbumsByGenre(String genre);

    List<Album> getAlbumsByYearReleased(Long yearReleased);

    List<Album> getAlbumsByArtist(String artist);

    List<Album> getAlbumByAlbumName(String albumName);
}
