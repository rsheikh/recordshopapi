package com.northcoders.recordshopapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.swing.text.DefaultHighlighter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    Long stockId;

    @Column
    Long quantityInStock;

    public Stock(Long quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
