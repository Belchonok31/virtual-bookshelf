package ru.belosludtsev.virtualbookshelf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

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

    @NotNull
    private String name;

    @NotNull
    private String authors;

    private String genre;

    private LocalDate dateIssue;

    private String description;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    @JsonIgnore
    private Shelf shelf;

    @JsonProperty("shelf_id")
    public Long getShelfId() {
        return shelf != null ? shelf.getId() : null;
    }

}
