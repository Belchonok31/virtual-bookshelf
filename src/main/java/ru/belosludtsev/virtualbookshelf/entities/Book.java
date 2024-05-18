package ru.belosludtsev.virtualbookshelf.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ISBN;

    private String name;

    private String authors;

    private String genre;

    private LocalDate dateIssue;

    private String description;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

}
