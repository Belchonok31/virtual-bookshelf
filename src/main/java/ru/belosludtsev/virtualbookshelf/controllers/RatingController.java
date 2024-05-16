package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Rating;
import ru.belosludtsev.virtualbookshelf.services.RatingServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rating")
public class RatingController {

    private final RatingServices ratingServices;

    @GetMapping
    public List<Rating> index() {
        return ratingServices.findAll();
    }

    @GetMapping("/{id}")
    public Rating show(@PathVariable("id") long id){
        return ratingServices.findOne(id);
    }

    @PostMapping
    public void save(Rating rating){
        ratingServices.save(rating);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id,  Rating ratingUpdate){
        ratingServices.update(id, ratingUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id){
        ratingServices.delete(id);
    }
}
