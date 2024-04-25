package com.telusko.jobms.job.external;

import lombok.Data;

@Data
public class Review {
    private Long id;
    private String title;
    private String description;
    private double ratings;
}
