package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.Statistics;
import ru.belosludtsev.virtualbookshelf.repositories.BookRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.StatisticsRepositories;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsServices {

    private final StatisticsRepositories statisticsRepositories;

    private final BookRepositories bookRepositories;

    public List<Statistics> findAll(){
        return statisticsRepositories.findAll();
    }

    public Statistics findOne(long id){
        return statisticsRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(long bookId, Statistics statistics){
        Optional<Book> optionalBook = bookRepositories.findById(bookId);
        optionalBook.ifPresent(statistics::setBook);
        statisticsRepositories.save(statistics);
    }

    @Transactional
    public void deleteByBookISBN(long id){
        statisticsRepositories.deleteById(id);
    }
}
