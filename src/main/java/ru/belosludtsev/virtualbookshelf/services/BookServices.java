package ru.belosludtsev.virtualbookshelf.services;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.BookImage;
import ru.belosludtsev.virtualbookshelf.repositories.BookImageRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.BookRepositories;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServices {

    private final BookRepositories bookRepositories;

    public List<Book> findAll() {
        return bookRepositories.findAll();
    }

    public Book findOne(long id) {
        return bookRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepositories.save(book);
    }

    @Transactional
    public void update(long id, Book bookUpdate) {
        bookUpdate.setId(id);
        bookRepositories.save(bookUpdate);
    }

    @Transactional
    public void delete(long id) {
        bookRepositories.deleteById(id);
    }

}
