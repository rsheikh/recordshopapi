package com.northcoders.recordshopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.model.Genre;
import com.northcoders.recordshopapi.model.Stock;
import com.northcoders.recordshopapi.service.AlbumManagerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

        return stockList;

    }

    @Test
    @DisplayName("Test method returns a list of all albums")
    public void testGetAllAlbums() throws Exception {
        List<Stock> stock = populateStock();
        List<Album> albums = populateAlbums(stock);

        when(mockAlbumManagerServiceImpl.getAllAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/recordshop"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Def Leppard"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].albumName").value("Hysteria"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearReleased").value(1987L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value("ROCK"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].stock.quantityInStock").value(20L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].artist").value("Ludovico Einaudi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].albumName").value("Una Mattina"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].yearReleased").value(2004L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].genre").value("CLASSIC"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$[8].stock").value(30L));

    }

}