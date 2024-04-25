package com.telusko.jobms.job;

import com.telusko.jobms.job.clients.CompanyClient;
import com.telusko.jobms.job.clients.ReviewClient;
import com.telusko.jobms.job.dto.JobDTO;
import com.telusko.jobms.job.external.ExtraCompany;
import com.telusko.jobms.job.external.Review;
import com.telusko.jobms.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CompanyClient companyClient;
    @Autowired
    private ReviewClient reviewClient;

    int attempt = 0;

    /**
     * Retrieves a list of jobDTO objects, each containing a Job and its associated Company.
     *
     * @return List<jobDTO>
     */
    @Override
   /* @CircuitBreaker(name = "companyBreaker",
                    fallbackMethod = "companyBreakFallback")*/
    /*@Retry(name = "companyBreaker",
            fallbackMethod = "companyBreakerFallback")*/
    @RateLimiter(name = "companyBreaker",
                fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        System.out.println("Attempts: "+ ++attempt);
        // Retrieve all jobs from the repository
        List<Job> jobs = jobRepository.findAll();

        // Create a list to store the jobDTO objects
        List<JobDTO> jobDTOs = new ArrayList<>();

        for(Job job : jobs) {
            JobDTO jobDTO = convertToDTO(job);
            jobDTOs.add(jobDTO);
        }

        // Return the list of jobDTO objects
        return jobDTOs;
    }

    public List<String> companyBreakerFallback(Exception e){
        List<String> list = new LinkedList<>();
        list.add("Dummy");
        return list;
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public JobDTO findById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDTO(job);
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setLocation(updatedJob.getLocation());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());

            jobRepository.save(job);
            return true;
        }
        return false;
    }

    public JobDTO convertToDTO (Job job) {
        // Retrieve the company associated with the job using REST template
        ExtraCompany company = companyClient.findCompanyById(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

        JobDTO jobDTO = JobMapper.mapToJobDTO(job, company, reviews);

    // Return the list of jobDTO objects
        return jobDTO;
    }
}
