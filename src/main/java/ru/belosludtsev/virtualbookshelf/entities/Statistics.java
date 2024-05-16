package ru.belosludtsev.virtualbookshelf.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name="statistics")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int additions;

    @OneToOne
    private Rating rating;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book books;
}
