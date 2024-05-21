package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Review;
import ru.belosludtsev.virtualbookshelf.services.ReviewServices;
import ru.belosludtsev.virtualbookshelf.services.StatisticsServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("client/{clientId}")
public class ReviewController {

    private final ReviewServices reviewServices;

    private final StatisticsServices statisticsServices;

    @GetMapping("/review/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewServices.findAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getAllReviewsByClientId(@PathVariable("clientId") long clientId) {
        List<Review> reviews = reviewServices.findAllByClientId(clientId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/review/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") long id) {
        Review review = reviewServices.findOne(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("book/{bookId}/review")
    public ResponseEntity<List<Review>> getAllReviewsByBookId(@PathVariable("bookId") long bookId) {
        List<Review> reviews = reviewServices.findAllByBookId(bookId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("book/{bookId}/review")
    public ResponseEntity<String> save(@PathVariable("clientId") long clientId,
                                       @PathVariable("bookId") long bookId,
                                       @RequestBody Review review) {
        reviewServices.save(clientId, bookId, review);
        statisticsServices.updateRating(review.getRating(), bookId);
        //        return ResponseEntity.ok("Review created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("book/{bookId}/review/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id,
                                         @PathVariable("bookId") long bookId,
                                         @RequestBody Review reviewUpdate) {
        if (reviewServices.findOne(id) != null) {
            reviewServices.update(id, reviewUpdate);
            statisticsServices.updateRating(reviewUpdate.getRating(), bookId);
            return ResponseEntity.ok("Review updated successfully");
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        if (reviewServices.findOne(id) != null) {
            reviewServices.delete(id);
            return ResponseEntity.ok("Review deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
