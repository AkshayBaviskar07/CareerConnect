package com.telusko.companyms.companies;


import com.telusko.companyms.companies.dto.ReviewMessage;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company getCompanyById(Long id);
    Company addCompany(Company company);
    boolean updateCompany(Long id, Company company);
    boolean deleteById(Long id);

    void updateCompanyRating(ReviewMessage reviewMessage);
}
