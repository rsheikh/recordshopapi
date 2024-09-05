package repository;

import model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumManagerRepository extends CrudRepository<Album, Long> {

}
