package ru.belosludtsev.virtualbookshelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;

@Repository
public interface ShelfRepositories extends JpaRepository<Shelf, Long> {
}
