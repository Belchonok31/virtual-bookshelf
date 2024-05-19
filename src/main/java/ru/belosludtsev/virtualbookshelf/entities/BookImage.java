package ru.belosludtsev.virtualbookshelf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="book_image")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class BookImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String url;

    @OneToOne
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    @JsonProperty("book_id")
    public Long getBookId() {
        return book != null ? book.getId() : null;
    }

}
