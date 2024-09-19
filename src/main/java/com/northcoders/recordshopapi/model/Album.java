package com.northcoders.recordshopapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    Long albumId;

    @Column
    String artist;

    @Column
    String albumName;

//    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Column
    Long yearReleased;

//    @Column
//    Genre genre;
    @Column
    String genre;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="stockId", referencedColumnName = "stockId")
    Stock stockId;

}
