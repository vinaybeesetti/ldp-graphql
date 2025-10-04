package com.tri.freelancemp.service;

import com.tri.freelancemp.entity.Application;
import com.tri.freelancemp.entity.Review;
import com.tri.freelancemp.entity.Role;
import com.tri.freelancemp.entity.User;
import com.tri.freelancemp.exceptions.ObjectNotFoundException;
import com.tri.freelancemp.pojo.ProjectReviewInput;
import com.tri.freelancemp.repository.ApplicationRepository;
import com.tri.freelancemp.repository.ReviewRepository;
import com.tri.freelancemp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ApplicationRepository applicationRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.applicationRepository = applicationRepository;
    }

    public Review submitProjectReview(ProjectReviewInput projectReviewInput) {
        int reviewerId = projectReviewInput.getReviewer_id();
        User reviewer = this.userRepository.findByIdAndRole(reviewerId, Role.CLIENT).orElseThrow(() -> new ObjectNotFoundException("Reviewer not found"));
        User reviewee = this.userRepository.findByIdAndRole(projectReviewInput.getReviewee_id(), Role.FREELANCER).orElseThrow(() -> new ObjectNotFoundException("Reviewee not found"));

        Application application = this.applicationRepository.findByIdAndApplicantId(projectReviewInput.getApplication_id(),
                projectReviewInput.getReviewee_id()).orElseThrow(() -> new ObjectNotFoundException("Application does not exist for this applicant"));


        Review review = new Review();
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setApplication(application);
        review.setComment(projectReviewInput.getComment());
        review.setRating(projectReviewInput.getRating());

        return reviewRepository.save(review);
    }

    public List<Review> getReviewByUserId(Integer userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException("User not found"));
        return this.reviewRepository.findByReviewee(user).orElseThrow(() -> new ObjectNotFoundException("Reviews not found"));
    }
}
