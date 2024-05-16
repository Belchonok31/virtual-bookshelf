package ru.belosludtsev.virtualbookshelf.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="rating")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rating;

    @OneToOne
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Review review;
}
