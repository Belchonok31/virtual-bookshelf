package ru.belosludtsev.virtualbookshelf.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

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

    private String name;

    private String authors;

    private String genre;

    private LocalDate dateIssue;

    private String description;

    @ManyToOne
    @JoinColumn(name = "statistics_id", referencedColumnName = "id")
    private Statistics statistics;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User owner;

    @OneToOne
    @JoinColumn(name="book_image_id", referencedColumnName = "id")
    private BookImage bookImage;

    @ManyToOne
    @JoinColumn(name = "shelf_id", referencedColumnName = "id")
    @JsonBackReference
    private Shelf shelf;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;
}
