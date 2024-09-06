package com.northcoders.recordshopapi.controller;

import com.northcoders.recordshopapi.exception.ItemNotFoundException;
import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.service.AlbumManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

}
