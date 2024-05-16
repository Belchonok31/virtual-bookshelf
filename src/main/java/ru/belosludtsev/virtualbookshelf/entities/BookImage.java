package ru.belosludtsev.virtualbookshelf.entities;

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

    @OneToOne
    @JoinColumn(name="book_image_id", referencedColumnName = "id")
    private BookImage bookImage;
}
