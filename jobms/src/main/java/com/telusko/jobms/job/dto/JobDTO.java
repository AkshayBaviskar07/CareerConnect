package com.telusko.jobms.job.dto;

import com.telusko.jobms.job.external.ExtraCompany;
import com.telusko.jobms.job.external.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private ExtraCompany company;
    private List<Review> reviews;
}
