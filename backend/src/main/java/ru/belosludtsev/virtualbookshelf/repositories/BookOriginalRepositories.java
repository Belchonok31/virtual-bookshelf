package ru.belosludtsev.virtualbookshelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belosludtsev.virtualbookshelf.entities.BookOriginal;

@Repository
public interface BookOriginalRepositories extends JpaRepository<BookOriginal, Long> {
}
