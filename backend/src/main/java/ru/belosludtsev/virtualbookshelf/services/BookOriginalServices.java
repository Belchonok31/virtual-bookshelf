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

    private final StatisticsServices statisticsServices;

    private final BookServices bookServices;

    private final ReviewServices reviewServices;

    public List<BookOriginal> findAll() {
        return bookOriginalRepositories.findAll();
    }

    public BookOriginal findOne(long id) {
        return bookOriginalRepositories.findById(id).orElse(null);
    }

    @Transactional
    public BookOriginal save(BookOriginal bookOriginal) {
        return bookOriginalRepositories.save(bookOriginal);
    }

    @Transactional
    public BookOriginal update(long id, BookOriginal bookOriginalUpdate) {
        bookOriginalUpdate.setId(id);
        return bookOriginalRepositories.save(bookOriginalUpdate);
    }

    @Transactional
    public void delete(long id) {
        statisticsServices.delete(id);
        reviewServices.deleteAllReviewsByBookOriginalId(id);
        bookServices.deleteAllBookByBookOriginalId(id);
        bookOriginalRepositories.deleteById(id);
    }
}
