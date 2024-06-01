package ru.belosludtsev.virtualbookshelf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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

    private String name;

    private String label;

    @ManyToOne
    @JoinColumn(name = "book_original_id")
    @JsonIgnore
    private BookOriginal bookOriginal;

    @JsonProperty
    public Long getBookOriginalId() {
        return bookOriginal != null ? bookOriginal.getId() : null;
    }

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    @JsonIgnore
    private Shelf shelf;

    @JsonProperty("shelf_id")
    public Long getShelfId() {
        return shelf != null ? shelf.getId() : null;
    }

}
