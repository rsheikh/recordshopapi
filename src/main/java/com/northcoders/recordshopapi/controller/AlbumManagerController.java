package com.northcoders.recordshopapi.controller;

import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.service.AlbumManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable("id") Long albumId) {
        Album album = albumManagerService.getAlbumById(albumId);
        System.out.println("Album: " + album);

        return new ResponseEntity<>(album, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Album> updateAlbumById(@RequestBody Album albumFromUrl, @PathVariable("id") Long albumId) {

        Album album = getAlbumById(albumId).getBody();
        Album updatedAlbum = albumManagerService.updateAlbumById(album, albumFromUrl);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("album", "/api/v1/recordshop/" + updatedAlbum.getAlbumId().toString());
        httpHeaders.add("successMessage", updatedAlbum + " updated successfully.");

        return new ResponseEntity<>(updatedAlbum, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbumById(@PathVariable("id") Long albumId) {
        albumManagerService.deleteAlbumById(albumId);

        return new ResponseEntity<>("Album deleted", HttpStatus.OK);
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> getAlbumsBy(
            @RequestParam(required=false, value="searchBy") String searchBy,
            @RequestParam(required=false, value="genre") String genre,
            @RequestParam(required=false, value="yearReleased") Long yearReleased,
            @RequestParam(required=false, value="artist") String artist) {

        if ("genre".equalsIgnoreCase(searchBy)) {
            return getAlbumsByGenre(genre);

        } else if ("yearReleased".equalsIgnoreCase(searchBy)) {
            return getAlbumsByYearReleased(yearReleased);

        } else if ("artist".equalsIgnoreCase(searchBy)) {
            return getAlbumsByArtist(artist);
        } else {
            //System.out.println("No search method defined. Return all albums??");
            return getAllAlbums();
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
