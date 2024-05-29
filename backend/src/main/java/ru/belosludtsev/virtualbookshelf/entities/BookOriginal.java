package ru.belosludtsev.virtualbookshelf.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name="book_original")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookOriginal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String name;

    private String authors;

    private String genre;

    private int dateIssue;

    private String description;
}
