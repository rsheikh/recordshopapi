package com.northcoders.recordshopapi.repository;

import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumManagerRepository extends CrudRepository<Album, Long> {

    List<Album> findAlbumsByGenre(Genre genreToSearch);

    List<Album> findAlbumsByYearReleased(Long yearReleased);

    List<Album> findByArtistContainingIgnoreCase(String artist);

    Album findByAlbumNameContainingIgnoreCase(String albumName);

    //TODO Add method to handle partial albumName match
    //TODO Implement corresponding unit tests
}
