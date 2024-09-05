package com.northcoders.recordshopapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.recordshopapi.model.Album;
import com.northcoders.recordshopapi.model.Genre;
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

    @BeforeEach
    void setUp() {
        mockMvcController = MockMvcBuilders.standaloneSetup(albumManagerController).build();
        mapper = new ObjectMapper();
    }

    public List<Album> populateAlbums() {
        albumList = new ArrayList<>();
        albumList.add(new Album(1L, "Def Leppard", "Hysteria", 1987L, Genre.ROCK, 20L));
        albumList.add(new Album(2L, "Def Leppard", "Adrenalize", 1992L, Genre.ROCK, 35L));
        albumList.add(new Album(3L, "U2", "The Joshua Tree", 1987L, Genre.ROCK, 40L));
        albumList.add(new Album(4L, "Guns N' Roses", "Appetite for Destruction", 1987L, Genre.ROCK, 10L));
        albumList.add(new Album(5L, "Guns N' Roses", "Use your Illusion 2 ", 1991L, Genre.ROCK, 30L));
        albumList.add(new Album(6L, "Wu Tang Clan", "Enter the Wu Tang Clan", 1993L, Genre.HIPHOP, 33L));
        albumList.add(new Album(7L, "2Pac", "All Eyez on Me", 1996L, Genre.HIPHOP, 20L));
        albumList.add(new Album(8L, "Michael Jackson", "Thriller", 1982L, Genre.POP, 5L));
        albumList.add(new Album(9L, "Ludovico Einaudi", "Una Mattina", 2004L, Genre.CLASSIC, 20L));
        albumList.add(new Album(10L, "Blake Shelton", "Red River Blue", 2004L, Genre.COUNTRY, 5L));

        return albumList;
    }

    @Test
    @DisplayName("Test method returns a list of all albums")
    public void testGetAllAlbums() throws Exception {
        List<Album>  albums = populateAlbums();

        when(mockAlbumManagerServiceImpl.getAllAlbums()).thenReturn(albums);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/api/v1/recordshop"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].artist").value("Def Leppard"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].albumName").value("Hysteria"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].yearReleased").value(1987L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value("ROCK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantityInStock").value(20L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].artist").value("Ludovico Einaudi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].albumName").value("Una Mattina"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].yearReleased").value(2004L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].genre").value("CLASSIC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[8].quantityInStock").value(20L));

    }

}