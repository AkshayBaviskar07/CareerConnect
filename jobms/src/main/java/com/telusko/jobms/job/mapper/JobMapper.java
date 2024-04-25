package com.telusko.jobms.job.mapper;

import com.telusko.jobms.job.external.ExtraCompany;
import com.telusko.jobms.job.Job;
import com.telusko.jobms.job.dto.JobDTO;
import com.telusko.jobms.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobDTO(Job job, ExtraCompany company, List<Review> reviews) {

        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);

        return jobDTO;
    }
}
