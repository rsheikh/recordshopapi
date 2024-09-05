package com.northcoders.recordshopapi.repository;

import com.northcoders.recordshopapi.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumManagerRepository extends CrudRepository<Album, Long> {

}
