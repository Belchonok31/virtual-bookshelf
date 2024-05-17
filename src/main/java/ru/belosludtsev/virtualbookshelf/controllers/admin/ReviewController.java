package ru.belosludtsev.virtualbookshelf.controllers.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Review;
import ru.belosludtsev.virtualbookshelf.services.ReviewServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewServices reviewServices;

    @GetMapping
    public List<Review> index() {
        return reviewServices.findAll();
    }

    @GetMapping("/{id}")
    public Review show(@PathVariable("id") long id) {
        return reviewServices.findOne(id);
    }

    @PostMapping
    public void save(@RequestBody Review review) {
        reviewServices.save(review);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable("id") long id, @RequestBody Review reviewUpdate) {
        reviewServices.update(id, reviewUpdate);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        reviewServices.delete(id);
    }
}
