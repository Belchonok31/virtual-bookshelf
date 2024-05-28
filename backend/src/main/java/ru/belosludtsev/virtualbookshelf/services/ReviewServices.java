package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Book;
import ru.belosludtsev.virtualbookshelf.entities.BookOriginal;
import ru.belosludtsev.virtualbookshelf.entities.Review;
import ru.belosludtsev.virtualbookshelf.entities.User;
import ru.belosludtsev.virtualbookshelf.repositories.BookOriginalRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.BookRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.ReviewRepositories;
import ru.belosludtsev.virtualbookshelf.repositories.UserRepositories;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServices {

    private final ReviewRepositories reviewRepositories;

    private final UserRepositories userRepositories;

    private final BookOriginalRepositories bookOriginalRepositories;

    public List<Review> findAll(){
        return reviewRepositories.findAll();
    }

    public List<Review> findAllByClientId(long clientId){
        return reviewRepositories.findAll().stream()
                .filter(review -> review.getUser().getId() == clientId)
                .collect(Collectors.toList());
    }

    public List<Review> findAllByBookOriginalId(long bookOriginalId){
        return reviewRepositories.findAll().stream()
                .filter(review -> review.getBookOriginal().getId() == bookOriginalId)
                .collect(Collectors.toList());
    }

    public Review findOne(long id){
        return reviewRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(long clientId, long bookOriginalId, Review review){

        Optional<User> optionalUser = userRepositories.findById(clientId);
        optionalUser.ifPresent(review::setUser);

        Optional<BookOriginal> optionalBookOriginal =bookOriginalRepositories.findById(bookOriginalId);
        optionalBookOriginal.ifPresent(review::setBookOriginal);

        reviewRepositories.save(review);
    }

    @Transactional
    public void update(long id, Review reviewUpdate){
        reviewUpdate.setId(id);
        reviewRepositories.save(reviewUpdate);
    }

    @Transactional
    public void delete(long id){
        reviewRepositories.deleteById(id);
    }

    @Transactional
    public void deleteAllReviewsByBookOriginalId(long bookOriginalId) {
        reviewRepositories.findAll().stream()
                .filter(review -> review.getBookOriginal().getId() == bookOriginalId)
                .map(Review::getId)
                .forEach(this::delete);
    }
}
