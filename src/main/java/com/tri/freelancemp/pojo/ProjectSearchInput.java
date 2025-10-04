package com.tri.freelancemp.pojo;

import lombok.Data;

@Data
public class ProjectSearchInput {

    private Float minBudget;
    private Float maxBudget;
    private String search;
}
