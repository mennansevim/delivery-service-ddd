package com.global.delivery.domain.services;

import com.global.delivery.domain.company.Company;
import com.global.delivery.infrastructure.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Mono<Company> saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Flux<Company> getByIdentityNumber(String companyIdentityNumber) {
        return companyRepository.findCompanyByIdentityNumber(companyIdentityNumber);
    }
}


