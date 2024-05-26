package ru.belosludtsev.virtualbookshelf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belosludtsev.virtualbookshelf.entities.BookImage;

@Repository
public interface BookImageRepositories extends JpaRepository<BookImage, Long> {
}
