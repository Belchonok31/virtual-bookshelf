package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.Shelf;
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

    private final BookImageServices bookImageServices;

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
    public void save(long shelfId, Book book) {
        // todo add check valid shelfId
        Optional<Shelf> optionalShelf = shelfRepositories.findById(shelfId);
        optionalShelf.ifPresent(book::setShelf);
        bookRepositories.save(book);
    }

    @Transactional
    public void update(long id, Book bookUpdate) {
        bookUpdate.setId(id);
        bookRepositories.save(bookUpdate);
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

}
