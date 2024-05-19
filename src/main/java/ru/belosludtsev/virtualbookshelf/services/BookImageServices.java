package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.BookImage;
import ru.belosludtsev.virtualbookshelf.repositories.BookImageRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.BookRepositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookImageServices {

    private final BookImageRepositories bookImageRepositories;

    private final BookRepositories bookRepositories;

    public List<BookImage> findAll() {
        return bookImageRepositories.findAll();
    }

    public List<BookImage> findAll(long bookId) {
        return bookImageRepositories.findAll().stream()
                .filter(bookImage -> bookImage.getBook().getId() == bookId)
                .collect(Collectors.toList());
    }

    public BookImage findOne(long id) {
        return bookImageRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(long bookId, BookImage bookImage) {
        Optional<Book> optionalBook = bookRepositories.findById(bookId);
        optionalBook.ifPresent(bookImage::setBook);
        bookImageRepositories.save(bookImage);
    }

    @Transactional
    public void update(long id, BookImage bookImageUpdate) {
        bookImageUpdate.setId(id);
        bookImageRepositories.save(bookImageUpdate);
    }

    @Transactional
    public void delete(long id) {
        bookImageRepositories.deleteById(id);
    }

    @Transactional
    public void deleteAllBookImageByBookId(long bookId) {
        bookImageRepositories.findAll().stream()
                .filter(bookImage -> bookImage.getBook().getId() == bookId)
                .map(BookImage::getId)
                .forEach(this::delete);
    }
}
