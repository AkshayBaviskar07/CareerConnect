package com.telusko.jobms.job.clients;

import com.telusko.jobms.job.external.ExtraCompany;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE",
        url = "${company-service.url}")
public interface CompanyClient {

    @GetMapping("/company/{companyId}")
    ExtraCompany findCompanyById(@PathVariable("companyId") Long companyId);
}
