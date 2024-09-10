package com.northcoders.recordshopapi.controller;

import com.northcoders.recordshopapi.exception.ParameterNotDefinedException;
import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.service.AlbumManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@EnableCaching
@RestController
@RequestMapping("/api/v1/recordshop")
public class AlbumManagerController {

    @Autowired
    AlbumManagerService albumManagerService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = albumManagerService.getAllAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Album> addAlbum(@RequestBody Album album) {
        Album newAlbum = albumManagerService.insertAlbum(album);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/recordshop/" + album.getAlbumId().toString());
        return new ResponseEntity<>(newAlbum, httpHeaders, HttpStatus.CREATED);
    }

    @Cacheable("albumsById")
    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable("id") Long albumId) {
        Album album = albumManagerService.getAlbumById(albumId);

        return new ResponseEntity<>(album, HttpStatus.FOUND);
    }

    @CacheEvict("albumsById")
    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Album> updateAlbumById(@RequestBody Album albumFromUrl, @PathVariable("id") Long albumId) {
        return new ResponseEntity<>(albumManagerService.updateAlbumById(albumId, albumFromUrl), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbumById(@PathVariable("id") Long albumId) {
        albumManagerService.deleteAlbumById(albumId);

        return new ResponseEntity<>("Album deleted", HttpStatus.OK);
    }

    @GetMapping("/album/{albumName}")
    public ResponseEntity<List<Album>> getAlbumByAlbumName(@PathVariable("albumName") String albumName) {
        List<Album> albums = albumManagerService.getAlbumByAlbumName(albumName);

        return new ResponseEntity<>(albums, HttpStatus.FOUND);
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAlbumsBy(
            @RequestParam(value="searchBy") String searchBy,
            @RequestParam(required=false, value="genre") String genre,
            @RequestParam(required=false, value="yearReleased") Long yearReleased,
            @RequestParam(required=false, value="artist") String artist) {

        if(genre == null && artist == null && yearReleased == null) {
            throw new ParameterNotDefinedException("Please enter a value in the corresponding searchBy field.");
        }

        if ("genre".equalsIgnoreCase(searchBy)) {
            return getAlbumsByGenre(genre);

        } else if ("yearReleased".equalsIgnoreCase(searchBy)) {
            return getAlbumsByYearReleased(yearReleased);

        } else if ("artist".equalsIgnoreCase(searchBy)) {
            return getAlbumsByArtist(artist);

        } else {
            throw new ParameterNotDefinedException(searchBy + " is not a valid searchBy parameter. You can search by genre, yearReleased or artist.");
        }
    }

    private ResponseEntity<List<Album>> getAlbumsByArtist(String artist) {
        List<Album> albums = albumManagerService.getAlbumsByArtist(artist);

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    public ResponseEntity<List<Album>> getAlbumsByGenre(String genre) {
        List<Album> albums = albumManagerService.getAlbumsByGenre(genre);

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    public ResponseEntity<List<Album>> getAlbumsByYearReleased(Long yearReleased) {
        List<Album> albums = albumManagerService.getAlbumsByYearReleased(yearReleased);

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    /*
    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAlbumsByGenre(@RequestParam(value="genre") String genre) {
        List<Album> albums = albumManagerService.getAlbumsByGenre(genre);

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

 */

}
