package ru.belosludtsev.virtualbookshelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belosludtsev.virtualbookshelf.entities.Book;

@Repository
public interface BookRepositories extends JpaRepository<Book, Long> {
}
