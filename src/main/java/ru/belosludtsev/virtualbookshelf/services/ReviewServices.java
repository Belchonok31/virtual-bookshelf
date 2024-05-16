package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Review;
import ru.belosludtsev.virtualbookshelf.repositories.ReviewRepositories;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServices {

    private final ReviewRepositories reviewRepositories;

    public List<Review> findAll(){
        return reviewRepositories.findAll();
    }

    public Review findOne(long id){
        return reviewRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(Review review){
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
}
