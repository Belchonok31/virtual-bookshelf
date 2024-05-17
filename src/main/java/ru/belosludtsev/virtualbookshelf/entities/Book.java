package ru.belosludtsev.virtualbookshelf.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="book")
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

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="book_image_id", referencedColumnName = "id")
    @JsonManagedReference
    private BookImage bookImage;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "statistics_id", referencedColumnName = "id")
    @JsonManagedReference
    private Statistics statistics;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User owner;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    @JsonBackReference
    private Shelf shelf;

}
