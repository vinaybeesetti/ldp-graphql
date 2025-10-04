package com.tri.freelancemp.resource;

import com.tri.freelancemp.entity.Review;
import com.tri.freelancemp.pojo.ProjectReviewInput;
import com.tri.freelancemp.service.ReviewService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @MutationMapping
    public Review submitProjectReview(@Argument ProjectReviewInput projectReviewInput) {
        return this.reviewService.submitProjectReview(projectReviewInput);
    }

    @QueryMapping
    public List<Review> getReviewByUserId(@Argument Integer id) {
        return this.reviewService.getReviewByUserId(id);
    }
}
