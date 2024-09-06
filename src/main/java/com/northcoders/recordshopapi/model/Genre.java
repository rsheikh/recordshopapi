package com.northcoders.recordshopapi.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

//@Entity
//@NoArgsConstructor
public enum Genre {
    ROCK("Rock"),
    HIPHOP("Hip-Hop"),
    POP("Pop"),
    COUNTRY("Country"),
    CLASSIC("Classic"),
    NOT_SPECIFIED("Not specified");

    String genreDescription;

    Genre(String description) {
        this.genreDescription = description;
    }

    public static Genre parseGenre(String input){
        try {
            return Genre.valueOf(input);
        } catch (IllegalArgumentException e) {
            return Genre.NOT_SPECIFIED;
        }
    }
}
