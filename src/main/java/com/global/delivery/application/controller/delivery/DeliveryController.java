package com.global.delivery.application.controller.delivery;

import com.global.delivery.application.controller.delivery.requests.CreateDeliveryRequest;
import com.global.delivery.domain.company.Company;
import com.global.delivery.domain.services.CompanyService;
import com.global.delivery.domain.services.DeliveryService;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

private final DeliveryService deliveryService;
private final CompanyService companyService;
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity<Object> createDelivery(@Valid @RequestBody @NonNull  CreateDeliveryRequest createDeliveryRequest) {

        Flux<Company> existCompanyFlux = companyService.getByIdentityNumber(createDeliveryRequest.company.identityNumber);
        if (!existCompanyFlux.hasElements().block())
        {
            String errorMessage = "Company with identity number " + createDeliveryRequest.company.identityNumber + " not found.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }


        var requestCompany = new CreateDeliveryRequest.RequestCompany();
        requestCompany.identityNumber = createDeliveryRequest.company.identityNumber;
        requestCompany.code = createDeliveryRequest.company.code;
        requestCompany.name = createDeliveryRequest.company.name;
        requestCompany.createdBy = createDeliveryRequest.company.createdBy;

        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.saveDelivery(createDeliveryRequest.ToDelivery()).block());
    }

}
