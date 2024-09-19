package com.northcoders.recordshopapi.repository;

import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumManagerRepository extends CrudRepository<Album, Long> {

//    List<Album> findAlbumsByGenre(Genre genreToSearch);
    List<Album> findAlbumsByGenre(String genreToSearch);

    List<Album> findAlbumsByYearReleased(Long yearReleased);

    List<Album> findByArtistContainingIgnoreCase(String artist);

    List<Album> findByAlbumNameContainingIgnoreCase(String albumName);
}
