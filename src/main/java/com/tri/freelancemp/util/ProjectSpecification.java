package com.tri.freelancemp.util;

import com.tri.freelancemp.entity.Project;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecification {

    public static Specification<Project> budgetGreaterThanEqual(Float minBudget) {
        return ((root, query, criteriaBuilder)
                -> minBudget == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("budget"), minBudget));
    }

    public static Specification<Project> budgetLessThanEqual(Float maxBudget) {
        return ((root, query, criteriaBuilder)
                -> maxBudget == null ? null : criteriaBuilder.lessThanOrEqualTo(root.get("budget"), maxBudget));
    }

    public static Specification<Project> keywordSearch(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isBlank()) {
                return null;
            }
            String likeKeyword = "%" + keyword + "%";
            return criteriaBuilder.or(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), likeKeyword),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likeKeyword));
        };
    }
}
