package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.BookOriginal;
import ru.belosludtsev.virtualbookshelf.entities.Statistics;
import ru.belosludtsev.virtualbookshelf.repositories.BookOriginalRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.BookRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.StatisticsRepositories;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsServices {

    private final StatisticsRepositories statisticsRepositories;

    public List<Statistics> findAll() {
        return statisticsRepositories.findAll();
    }

    public Statistics findOne(long id) {
        return statisticsRepositories.findById(id).orElse(null);
    }

    public Statistics findOneByBookOriginalId(long bookOriginalId) {
        return statisticsRepositories.findAll().stream()
                .filter(statistics -> statistics.getBookOriginal().getId() == bookOriginalId)
                .findFirst().orElse(null);
    }

    @Transactional
    public void save(BookOriginal bookOriginal) {
        var statistics = new Statistics();
        statistics.setAdditions(0);
        statistics.setRating(0f);
        statistics.setBookOriginal(bookOriginal);
        statisticsRepositories.save(statistics);
    }

    @Transactional
    public void updateAdditions(long bookOriginalId) {
        Optional<Statistics> optionalStatistics = statisticsRepositories.findAll().stream()
                .filter(statistics -> statistics.getBookOriginal().getId() == bookOriginalId)
                .findFirst();
        if (optionalStatistics.isPresent()) {
            var statistics = optionalStatistics.get();
            statistics.setAdditions(statistics.getAdditions() + 1);
        }
    }

    @Transactional
    public void updateRating(float ratingOfReview, long bookOriginalId) {
        Optional<Statistics> optionalStatistics = statisticsRepositories.findAll().stream()
                .filter(statistics -> statistics.getBookOriginal().getId() == bookOriginalId)
                .findFirst();
        if (optionalStatistics.isPresent()) {
            var statistics = optionalStatistics.get();
            statistics.setNumberOfReviews(statistics.getNumberOfReviews() + 1);
            statistics.setRating(setNewRating(statistics.getRating(), ratingOfReview,statistics.getNumberOfReviews()));
        }
    }

    private float setNewRating(float oldRating, float ratingOfReview, int numberOfReviews) {
       return (oldRating + ratingOfReview) / numberOfReviews;
    }

    @Transactional
    public void delete(long bookOriginalId) {
        Optional<Statistics> optionalStatistics = statisticsRepositories.findAll().stream()
                .filter(statistics -> statistics.getBookOriginal().getId() == bookOriginalId)
                .findFirst();
        optionalStatistics.ifPresent(statisticsRepositories::delete);
    }
}
