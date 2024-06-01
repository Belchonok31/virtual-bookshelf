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
public class ReviewController {

    private final ReviewServices reviewServices;

    private final StatisticsServices statisticsServices;

    private final AuthenticatedUserService authenticatedUserService;

    @GetMapping("/review/all")
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewServices.findAll();
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getAllReviewsByClientId() {
        User user = authenticatedUserService.getAuthenticatedUser();
        List<Review> reviews = reviewServices.findAllByClientId(user.getId());
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

    @GetMapping("/bookOriginal/{bookOriginalId}/review")
    public ResponseEntity<List<Review>> getAllReviewsByBookId(@PathVariable("bookOriginalId") long bookOriginalId) {
        List<Review> reviews = reviewServices.findAllByBookOriginalId(bookOriginalId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/bookOriginal/{bookOriginalId}/review")
    public Review save(@PathVariable("bookOriginalId") long bookOriginalId,
                                       @RequestBody Review review) {
        User user = authenticatedUserService.getAuthenticatedUser();
        Review createReview = reviewServices.save(user.getId(), bookOriginalId, review);
        if (createReview != null) {
            statisticsServices.updateRating(review.getRating(), bookOriginalId);
            return createReview;
        }
        return null;
        //        return ResponseEntity.ok("Review created successfully");
    }

    @PatchMapping("/bookOriginal/{bookOriginalId}/review/{id}")
    public Review update(@PathVariable("id") long id,
                                         @PathVariable("bookOriginalId") long bookOriginalId,
                                         @RequestBody Review reviewUpdate) {
        if (reviewServices.findOne(id) != null) {
            Review updateReview = reviewServices.update(id, reviewUpdate);
            statisticsServices.updateRating(reviewUpdate.getRating(), bookOriginalId);
            return updateReview;
        } else return null;
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        if (reviewServices.findOne(id) != null) {
            statisticsServices.deleteReview(id);
            reviewServices.delete(id);
            return ResponseEntity.ok("Review deleted successfully");
        } else return ResponseEntity.notFound().build();
    }
}
