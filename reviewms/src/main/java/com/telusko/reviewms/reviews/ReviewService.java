package com.telusko.reviewms.reviews;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Boolean addReview(Long companyId, Review review);
    Review getReview(Long reviewId);
    boolean updateReviewById(Long reviewId, Review updatedReview);
    boolean deleteReviewById(Long reviewId);
}
