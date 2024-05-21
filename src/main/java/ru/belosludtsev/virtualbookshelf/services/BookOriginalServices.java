package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.BookOriginal;
import ru.belosludtsev.virtualbookshelf.repositories.BookOriginalRepositories;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookOriginalServices {

    private final BookOriginalRepositories bookOriginalRepositories;

    public List<BookOriginal> findAll() {
        return bookOriginalRepositories.findAll();
    }

    public BookOriginal findOne(long id) {
        return bookOriginalRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(BookOriginal bookOriginal) {
        bookOriginalRepositories.save(bookOriginal);
    }

    @Transactional
    public void update(long id, BookOriginal bookOriginalUpdate) {
        bookOriginalUpdate.setId(id);
        bookOriginalRepositories.save(bookOriginalUpdate);
    }

    @Transactional
    public void delete(long id) {
        bookOriginalRepositories.deleteById(id);
    }
}
