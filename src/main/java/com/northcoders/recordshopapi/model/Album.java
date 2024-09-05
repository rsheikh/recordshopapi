package com.northcoders.recordshopapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    Long albumId;

    @Column
    String artist;

    @Column
    String albumName;

    @Column
    Long yearReleased;

    @Column
    Genre genre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stockId", referencedColumnName = "stockId")
    Stock stockId;


}
