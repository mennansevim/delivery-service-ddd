package com.global.delivery.application.controller.company;

import com.global.delivery.application.controller.company.requests.CreateCompanyRequest;
import com.global.delivery.domain.company.Company;
import com.global.delivery.domain.services.CompanyService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Company> createCompany(@RequestBody @NonNull CreateCompanyRequest createCompanyRequest) {

        Flux<Company> existCompanyFlux = companyService.getByIdentityNumber(createCompanyRequest.identityNumber);
        if (existCompanyFlux.hasElements().block())
            return ResponseEntity.ok(existCompanyFlux.blockFirst());

        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.saveCompany(createCompanyRequest.ToCompany()).block());
    }
}
