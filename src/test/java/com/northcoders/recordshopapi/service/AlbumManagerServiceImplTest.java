package com.northcoders.recordshopapi.service;

import com.northcoders.recordshopapi.exception.ItemNotFoundException;
import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.model.Genre;
import com.northcoders.recordshopapi.model.Stock;
import com.northcoders.recordshopapi.repository.AlbumManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AlbumManagerServiceImplTest {

    @Mock
    private AlbumManagerRepository mockAlbumManagerRepository;

    @InjectMocks
    private AlbumManagerServiceImpl albumManagerServiceImpl;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Return an Item Not Found error msg when trying to find album with an invalid Id.")
    public void testGetAlbumByIdReturnsErrorMsg() {

        Album album = new Album(12L, "Sia", "1000 Forms of Fear", 2014L, Genre.POP, new Stock(12L, 44L));
        String errMsg = "Record with id '13' cannot be found";

        // We have a test double for the AlbumManagerRepository. This is a stub
        try {
            albumManagerServiceImpl.getAlbumById(13L);
        } catch (ItemNotFoundException infe) {
            assertThat(infe).hasMessageContaining(errMsg);
        }
    }

    @Test
    @DisplayName("Return an Item Not Found error msg when trying to find album with an invalid Id.")
    public void testUpdateAlbumByIdReturnsErrorMsg() {

        Album album = new Album(12L, "Sia", "1000 Forms of Fear", 2014L, Genre.POP, new Stock(12L, 44L));
        String errMsg = "Album with id '25' cannot be found to be updated";

        try {
            albumManagerServiceImpl.updateAlbumById(25L, album);
        } catch (ItemNotFoundException infe) {
            assertThat(infe).hasMessageContaining(errMsg);
        }
    }

    @Test
    @DisplayName("Test that throws an exception with an Invalid Id ")
    public void testDeleteAlbumByIdThrowsItemNotFoundException() {
        Exception exception = assertThrows(ItemNotFoundException.class, () -> {
            albumManagerServiceImpl.deleteAlbumById(999999999999999L);;
        });

        String expectedMessage = "Album with id '999999999999999' cannot be found to be deleted";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}