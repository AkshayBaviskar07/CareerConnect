package com.telusko.reviewms.reviews;

import com.telusko.reviewms.reviews.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private ReviewMessageProducer reviewMessageProducer;
    public ReviewController(ReviewService reviewService,
                            ReviewMessageProducer reviewMessageProducer){
        this.reviewService=reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        List<Review> reviews =
                reviewService.getAllReviews(companyId);
        if(!reviews.isEmpty())
            return new ResponseEntity<>(reviews, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review,
                                            @RequestParam Long companyId){
        boolean isAdded = reviewService.addReview(companyId, review);
        if(isAdded) {
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review added", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Unable to add review", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId){
        Review review = reviewService.getReview(reviewId);
        if(review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long reviewId,
                                                   @RequestBody Review updatedReview){
        boolean isUpdated = reviewService
                .updateReviewById(reviewId, updatedReview);

        if(isUpdated)
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId){
        boolean isDeleted = reviewService
                .deleteReviewById(reviewId);

        if(isDeleted)
            return new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/averageRatings")
    public Double getAverageRatings(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        return reviews.stream().mapToDouble(Review::getRatings).average()
                .orElse(0.0);
    }

}
