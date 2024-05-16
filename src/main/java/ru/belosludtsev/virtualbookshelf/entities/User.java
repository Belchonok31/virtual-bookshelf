package ru.belosludtsev.virtualbookshelf.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name="user")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String middleName;

    private String email;

    private String password;

    private String role;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    @OneToMany(mappedBy = "user")
    private List<Shelf> shelves;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

}
