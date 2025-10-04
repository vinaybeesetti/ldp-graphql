package com.tri.freelancemp.repository;

import com.tri.freelancemp.entity.Review;
import com.tri.freelancemp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    Optional<List<Review>> findByReviewee(User reviewee);
}
