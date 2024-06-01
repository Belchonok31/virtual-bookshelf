package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.BookOriginal;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;
import ru.belosludtsev.virtualbookshelf.repositories.BookOriginalRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.BookRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.ShelfRepositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServices {

    private final BookRepositories bookRepositories;

    private final ShelfRepositories shelfRepositories;

    private final BookOriginalRepositories bookOriginalRepositories;

    private final BookImageServices bookImageServices;

    private final ReviewServices reviewServices;

    public List<Book> findAll() {
        return bookRepositories.findAll();
    }

    public List<Book> findAll(long shelfId) {
        return bookRepositories.findAll().stream()
                .filter(book -> book.getShelf().getId() == shelfId)
                .collect(Collectors.toList());
    }

    public Book findOne(long id) {
        return bookRepositories.findById(id).orElse(null);
    }

    @Transactional
    public Book save(long shelfId, long bookOriginalId, Book book) {

        // todo add check valid shelfId
        Optional<Shelf> optionalShelf = shelfRepositories.findById(shelfId);
        optionalShelf.ifPresent(book::setShelf);

        // todo add check valid bookOriginalId
        Optional<BookOriginal> optionalBookOriginal = bookOriginalRepositories.findById(bookOriginalId);
        optionalBookOriginal.ifPresent(book::setBookOriginal);

        return bookRepositories.save(book);
    }

    @Transactional
    public Book update(long id, Book bookUpdate) {
        bookUpdate.setId(id);
        return bookRepositories.save(bookUpdate);
    }

    @Transactional
    public void delete(long id) {
        bookImageServices.deleteAllBookImageByBookId(id);
        bookRepositories.deleteById(id);
    }

    @Transactional
    public void deleteAllBookByShelfId(long shelfId) {
        bookRepositories.findAll().stream()
                .filter(book -> book.getShelf().getId() == shelfId)
                .map(Book::getId)
                .forEach(this::delete);
    }

    @Transactional
    public void deleteAllBookByBookOriginalId(long bookOriginalId) {
        bookRepositories.findAll().stream()
                .filter(book -> book.getBookOriginal().getId() == bookOriginalId)
                .map(Book::getId)
                .forEach(this::delete);
    }

}
