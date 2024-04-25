package com.telusko.companyms.companies;

import com.telusko.companyms.companies.clients.ReviewClient;
import com.telusko.companyms.companies.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{
    private CompanyRepository companyRepository;
    private ReviewClient reviewClient;
    public CompanyServiceImpl(CompanyRepository companyRepository,
                              ReviewClient reviewClient) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> companyOptional =
                companyRepository.findById(id);

        if(companyOptional.isPresent()){
            Company companyToUpdate =
                    companyOptional.get();
            companyToUpdate.setName(company.getName());
            companyToUpdate.setDescription(company.getDescription());

            companyRepository.save(companyToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company = companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Company not found " + reviewMessage.getCompanyId()));

        Double avgRating = reviewClient.getAverageRatings(reviewMessage.getCompanyId());
        company.setRating(avgRating);
        companyRepository.save(company);
    }
}
