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
    public ResponseEntity<Optional<Album>> getAlbumById(@PathVariable("id") Long albumId) {
        Optional<Album> album = albumManagerService.getAlbumById(albumId);

        return new ResponseEntity<>(album, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> updateAlbumById(@RequestBody Album albumFromUrl, @PathVariable("id") Long albumId) {
        Album album = new Album();

        Optional<Album> albumToUpdate = Optional.ofNullable(albumManagerService.getAlbumById(albumId)
                .orElseThrow(() -> new ItemNotFoundException("Album with id: " + albumId + " does not exist.")));

        if(albumToUpdate.isPresent()) {
            album = albumManagerService.updateAlbumById(albumToUpdate, albumFromUrl);
//            albumToUpdate.get().setAlbumName(albumFromUrl.getAlbumName());
//            albumToUpdate.get().setArtist(albumFromUrl.getArtist());
//            albumToUpdate.get().setYearReleased(albumFromUrl.getYearReleased());
//            albumToUpdate.get().setGenre(albumFromUrl.getGenre());
//            albumToUpdate.get().getStockId().setQuantityInStock(albumFromUrl.getStockId().getStockId());
        }

        return new ResponseEntity<>(album.getAlbumName() + " updated successfully.", HttpStatus.OK);

    }


}
