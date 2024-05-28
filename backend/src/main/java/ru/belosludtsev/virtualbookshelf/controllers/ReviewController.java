package ru.belosludtsev.virtualbookshelf.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.belosludtsev.virtualbookshelf.entities.Review;
import ru.belosludtsev.virtualbookshelf.entities.User;
import ru.belosludtsev.virtualbookshelf.services.AuthenticatedUserService;
import ru.belosludtsev.virtualbookshelf.services.ReviewServices;
import ru.belosludtsev.virtualbookshelf.services.StatisticsServices;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewServices reviewServices;

    private final StatisticsServices statisticsServices;

    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping("/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewServices.findAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviewsByClientId() {
        User user = authenticatedUserService.getAuthenticatedUser();
        List<Review> reviews = reviewServices.findAllByClientId(user.getId());
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") long id) {
        Review review = reviewServices.findOne(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("bookOriginal/{bookOriginalId}/review")
    public ResponseEntity<List<Review>> getAllReviewsByBookId(@PathVariable("bookOriginalId") long bookOriginalId) {
        List<Review> reviews = reviewServices.findAllByBookOriginalId(bookOriginalId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("bookOriginal/{bookOriginalId}/review")
    public ResponseEntity<String> save(@PathVariable("clientId") long clientId,
                                       @PathVariable("bookOriginalId") long bookOriginalId,
                                       @RequestBody Review review) {
        reviewServices.save(clientId, bookOriginalId, review);
        statisticsServices.updateRating(review.getRating(), bookOriginalId);
        //        return ResponseEntity.ok("Review created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("bookOriginal/{bookOriginalId}/review/{id}")
    public ResponseEntity<String> update(@PathVariable("id") long id,
                                         @PathVariable("bookOriginalId") long bookOriginalId,
                                         @RequestBody Review reviewUpdate) {
        if (reviewServices.findOne(id) != null) {
            reviewServices.update(id, reviewUpdate);
            statisticsServices.updateRating(reviewUpdate.getRating(), bookOriginalId);
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
