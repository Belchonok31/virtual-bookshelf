package ru.belosludtsev.virtualbookshelf.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.belosludtsev.virtualbookshelf.entities.Rating;
import ru.belosludtsev.virtualbookshelf.repositories.RatingRepositories;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServices {

    private final RatingRepositories ratingRepositories;

    public List<Rating> findAll(){
        return ratingRepositories.findAll();
    }

    public Rating findOne(long id){
        return ratingRepositories.findById(id).orElse(null);
    }

    @Transactional
    public void save(Rating rating){
        ratingRepositories.save(rating);
    }

    @Transactional
    public void update(long id, Rating ratingUpdate){
        ratingUpdate.setId(id);
        ratingRepositories.save(ratingUpdate);
    }

    @Transactional
    public void delete(long id){
        ratingRepositories.deleteById(id);
    }
}
