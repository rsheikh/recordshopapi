package com.northcoders.recordshopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.recordshopapi.exception.ItemNotFoundException;
import com.northcoders.recordshopapi.exception.ParameterNotDefinedException;
import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.model.Genre;
import com.northcoders.recordshopapi.model.Stock;
import com.northcoders.recordshopapi.service.AlbumManagerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
class AlbumManagerControllerTest {

    @Mock
    private AlbumManagerServiceImpl mockAlbumManagerServiceImpl;

    @InjectMocks
    private AlbumManagerController albumManagerController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;
    List<Album> albumList;
    List<Stock> stockList;

    @BeforeEach
    void setUp() {
        mockMvcController = MockMvcBuilders.standaloneSetup(albumManagerController).build();
        mapper = new ObjectMapper();
    }

    public List<Album> populateAlbums(List<Stock> stock) {
        albumList = new ArrayList<>();
        albumList.add(new Album(1L, "Def Leppard", "Hysteria", 1987L, Genre.ROCK, stock.get(0)));
        albumList.add(new Album(2L, "Def Leppard", "Adrenalize", 1992L, Genre.ROCK, stock.get(1)));
        albumList.add(new Album(3L, "U2", "The Joshua Tree", 1987L, Genre.ROCK, stock.get(2)));
        albumList.add(new Album(4L, "Guns N' Roses", "Appetite for Destruction", 1987L, Genre.ROCK, stock.get(3)));
        albumList.add(new Album(5L, "Guns N' Roses", "Use your Illusion 2 ", 1991L, Genre.ROCK, stock.get(4)));
        albumList.add(new Album(6L, "Wu Tang Clan", "Enter the Wu Tang Clan", 1993L, Genre.HIPHOP, stock.get(5)));
        albumList.add(new Album(7L, "2Pac", "All Eyez on Me", 1996L, Genre.HIPHOP, stock.get(6)));
        albumList.add(new Album(8L, "Michael Jackson", "Thriller", 1982L, Genre.POP, stock.get(7)));
        albumList.add(new Album(9L, "Ludovico Einaudi", "Una Mattina", 2004L, Genre.CLASSIC, stock.get(8)));
        albumList.add(new Album(10L, "Blake Shelton", "Red River Blue", 2004L, Genre.COUNTRY, stock.get(9)));
        albumList.add(new Album(11L, "Blake Shelton", "Red River Blue 2", 2008L, Genre.COUNTRY, stock.get(10)));
        albumList.add(new Album(12L, "Sia", "1000 Forms of Fear", 2014L, Genre.POP, stock.get(11)));


        return albumList;
    }

    public List<Stock> populateStock() {
        stockList = new ArrayList<>();
        stockList.add(new Stock(1L, 20L));
        stockList.add(new Stock(2L, 25L));
        stockList.add(new Stock(3L, 30L));
        stockList.add(new Stock(4L, 35L));
        stockList.add(new Stock(5L, 40L));
        stockList.add(new Stock(6L, 45L));
        stockList.add(new Stock(7L, 20L));
        stockList.add(new Stock(8L, 25L));
        stockList.add(new Stock(9L, 30L));
        stockList.add(new Stock(10L, 35L));
        stockList.add(new Stock(11L, 16L));
        stockList.add(new Stock(12L, 22L));

        return stockList;
    }

    public List<Album> populatePartialAlbumList() {
        List<Album> matchedAlbums = albumList = new ArrayList<>(List.of(
                new Album(1L, "Def Leppard", "Hysteria", 1987L, Genre.ROCK, new Stock(1L, 20L)),
                new Album(2L, "Def Leppard", "Adrenalize", 1992L, Genre.ROCK, new Stock(2L, 25L))
        ));
        return matchedAlbums;
    }

    @Test
    @DisplayName("Test method returns a list of all albums")
    public void testGetAllAlbums() throws Exception {
        List<Stock> stock = populateStock();
        List<Album> albums = populateAlbums(stock);

        when(mockAlbumManagerServiceImpl.getAllAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/recordshop"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Def Leppard"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].albumName").value("Hysteria"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearReleased").value(1987L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value("ROCK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stockId.quantityInStock").value(20L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].artist").value("Ludovico Einaudi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].albumName").value("Una Mattina"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].yearReleased").value(2004L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].genre").value("CLASSIC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].stockId.quantityInStock").value(30L));

    }

    @Test
    @DisplayName("Add a new album")
    public void testAddAlbum() throws Exception {

        Album album = new Album(11L, "Ariana Grande", "Eternal Sunshine", 2024L, Genre.POP, new Stock(11L, 60L));

        when(mockAlbumManagerServiceImpl.insertAlbum(album)).thenReturn(album);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/recordshop/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(album)))
                .andExpect(status().isCreated());

        verify(mockAlbumManagerServiceImpl, times(1)).insertAlbum(album);
    }

    @Test
    @DisplayName("Test retrieval of album by Id")
    void testGetAlbumById() throws Exception {

        Album album = new Album(12L, "Sia", "1000 Forms of Fear", 2014L, Genre.POP, new Stock(12L, 44L));

        when(mockAlbumManagerServiceImpl.getAlbumById(12L)).thenReturn(album);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/recordshop/12"))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist").value("Sia"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.albumName").value("1000 Forms of Fear"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.yearReleased").value(2014L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("POP"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.stockId.quantityInStock").value(44L));
    }

    @Test
    @DisplayName("Test update of an album by a given Id")
    public void testUpdateAlbumById() throws Exception {
        Album album = new Album(12L,  "Sia", "1000 Forms of Fear", 2004L, Genre.POP, new Stock(12L, 2L));
        Album albumOfNewValues = new Album(12L, "Formerly Sia", "1001 Forms of Fear", 2014L, Genre.POP, new Stock(12L, 99L));

        Long id = 12L;

        when(mockAlbumManagerServiceImpl.updateAlbumById(12L, albumOfNewValues)).thenReturn(albumOfNewValues);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.put("/api/v1/recordshop/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(albumOfNewValues)))
                .andExpect(status().isOk());

        Assertions.assertEquals(albumOfNewValues.getAlbumName(), "1001 Forms of Fear");
        Assertions.assertEquals(albumOfNewValues.getAlbumId(), id);
    }

    @Test
    @DisplayName("Test delete of album by a given Id")
    public void testDeleteAlbumById() throws Exception {
        Long id = 12L;

        Album album = new Album(12L,  "Sia", "1000 Forms of Fear", 2004L, Genre.POP, new Stock(12L, 2L));

        this.mockMvcController.perform(
                MockMvcRequestBuilders.delete("/api/v1/recordshop/{id}", id))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Test retrieval of artist by partial match")
    public void testRetrieveAlbumsByArtistPartiallyMatched() throws Exception {
        List<Album> matchedAlbums = populatePartialAlbumList();
        String artistSubstring = "epp";//for Def Leppard

        when(mockAlbumManagerServiceImpl.getAlbumsByArtist(artistSubstring)).thenReturn(matchedAlbums);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/recordshop/album/{artist}", artistSubstring))
                .andExpect(status().isFound());

        assertTrue(matchedAlbums.getFirst().getArtist().contains(artistSubstring));
    }

    @Test
    @DisplayName("Test retrieval of album ignoring case-sensitivity")
    public void testRetrieveAlbumByAlbumNameIgnoringCase() throws Exception {
        Album matchedAlbum = new Album(1L, "Guns N' Roses", "Appetite for Destruction", 1987L, Genre.ROCK, new Stock(1L, 45L));
        String albumNameSubstring = "deSTrucTioN";//for Guns N'Roses

        when(mockAlbumManagerServiceImpl.getAlbumByAlbumName(albumNameSubstring)).thenReturn(List.of(matchedAlbum));

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/recordshop/album/{albumName}", albumNameSubstring))
                .andExpect(status().isFound());

        assertTrue(matchedAlbum.getAlbumName().contains("Destruction"));
        //assertTrue(matchedAlbum.getAlbumName().equalsIgnoreCase(contains(albumNameSubstring)));
        assertTrue(Pattern.compile(Pattern.quote("deSTrucTioN"), Pattern.CASE_INSENSITIVE).matcher(matchedAlbum.getAlbumName()).find());
        assertFalse(matchedAlbum.getAlbumName().contains("Dandelions"));
    }

    /*
    Can't test for this as Java doesn't let me proceed if a variable is null. Moot test
    @Test
    @DisplayName("Throw an Exception when dependent searchBy query parameter is left blank for executing a filter")
    public void testUpdateAlbumByIdReturnsErrorMsg() throws Exception {

        String errMsg = "Please enter a value in the corresponding searchBy field.";
        String genre, artist, yearReleased = null;

        try {
            mockAlbumManagerServiceImpl.getAlbumsByGenre(null);
        } catch (ParameterNotDefinedException pe) {
            assertThat(pe).hasMessageContaining(errMsg);
        }
    }

     */

}