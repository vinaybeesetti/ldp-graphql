package com.tri.freelancemp.pojo;

import lombok.Data;

@Data
public class ProjectReviewInput {
    private int reviewer_id;
    private int reviewee_id;
    private Long application_id;
    private String comment;
    private int rating;
}
