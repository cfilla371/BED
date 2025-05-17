package org.launchcode.BingeBuddy.service;


import org.launchcode.BingeBuddy.data.ReviewRepository;
import org.launchcode.BingeBuddy.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }


    public List<Review> getAllUserReviewsByUserID(Integer id){
        return reviewRepository.findByUserId(id);
    }

}
