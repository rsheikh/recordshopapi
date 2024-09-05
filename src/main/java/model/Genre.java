package model;

public enum Genre {
    ROCK("Rock"),
    HIPHOP("Hip-Hop"),
    POP("Pop"),
    COUNTRY("Country"),
    CLASSIC("Classic");

    String genreDescription;

    Genre(String description) {
        this.genreDescription = description;
    }
}
