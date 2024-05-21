package ru.belosludtsev.virtualbookshelf.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "statistics")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int additions;

    private int rating;

    @OneToOne
    @JoinColumn(name = "book_original_id")
    @JsonIgnore
    private BookOriginal bookOriginal;

    @JsonProperty("book_id")
    public Long getBookId() {
        return bookOriginal != null ? bookOriginal.getId() : null;
    }
}
