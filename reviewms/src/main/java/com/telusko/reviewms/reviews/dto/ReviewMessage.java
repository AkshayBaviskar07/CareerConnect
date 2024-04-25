package com.telusko.reviewms.reviews.dto;

import lombok.Data;

@Data
public class ReviewMessage {

    private Long id;
    private String title;
    private String description;
    private double ratings;
    private Long companyId;
}
