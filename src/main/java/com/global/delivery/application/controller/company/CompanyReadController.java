package com.global.delivery.application.controller.company;

import com.global.delivery.application.controller.company.requests.CompanyReadQueryRequest;
import com.global.delivery.application.exception.CompanyNotFoundException;
import com.global.delivery.domain.company.Company;
import com.global.delivery.domain.services.CompanyService;
import com.global.delivery.infrastructure.repository.CompanyRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/companies-read")
public class CompanyReadController {

    private final CompanyRepository companyRepository;
    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<Object> getCompaniesByQuery(@RequestBody CompanyReadQueryRequest request) throws CompanyNotFoundException {

        StringBuilder filter = new StringBuilder();
        filter.append(" 1=1 ");
        if (request.Name != null)
        {
            filter.append(String.format(" AND company.name = '%s'", request.Name));
        }
        if (request.IdentityNumber != null)
        {
            filter.append(String.format(" AND company.identityNumber = '%s'", request.IdentityNumber));
        }
        if (request.Code != null)
        {
            filter.append(String.format(" AND company.code = '%s'", request.Code));
        }
        if (request.CreatedBy != null)
        {
            filter.append(String.format(" AND company.createdBy = '%s'", request.CreatedBy));
        }

        if (request.BeginDeliveryDate != null)
        {
            filter.append(String.format(" AND company.creationDate > %s", request.BeginDeliveryDate));
        }

        if (request.EndDeliveryDate != null)
        {
            filter.append(String.format(" AND company.creationDate < %s", request.EndDeliveryDate));
        }

        Flux<List<Company>> companyFlux = companyRepository.findCompaniesByCompanyAndCustomField(filter.toString() );
        if (companyFlux.hasElements().block())
        {
            return ResponseEntity.ok(companyFlux.collectList().block());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found");

    }
}
