package com.telusko.reviewms.reviews;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository repository;
    public ReviewServiceImpl(ReviewRepository repository){
        this.repository=repository;;
    }
    @Override
    public List<Review> getAllReviews(Long companyId) {
        return repository.findByCompanyId(companyId);
    }

    @Override
    public Boolean addReview(Long companyId, Review review) {
        if(companyId != null && review != null){
            review.setCompanyId(companyId);
            repository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        return repository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReviewById(Long reviewId, Review updatedReview) {
        Review review = repository.findById(reviewId).orElse(null);
       if(review != null){
           review.setTitle(updatedReview.getTitle());
           review.setDescription(updatedReview.getDescription());
           review.setRatings(updatedReview.getRatings());
           review.setCompanyId(updatedReview.getCompanyId());
           repository.save(review);
           return true;
       }
       return false;
    }

    @Override
    public boolean deleteReviewById(Long reviewId) {
        Review review = repository.findById(reviewId).orElse(null);

        if(review !=null){
            repository.delete(review);
            return true;
        }
        return false;
    }
}
