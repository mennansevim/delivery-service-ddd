package com.global.delivery.application.controller.delivery;

import com.global.delivery.application.controller.delivery.requests.DeliveryReadQueryRequest;
import com.global.delivery.application.exception.DeliveryNotFoundException;
import com.global.delivery.domain.delivery.Delivery;
import com.global.delivery.infrastructure.repository.DeliveryRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static org.springframework.data.couchbase.core.query.QueryCriteria.where;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deliveries-read")
public class DeliveryReadController {

    private final DeliveryRepository deliveryRepository;

    @GetMapping
    @ResponseStatus(OK)
    public ResponseEntity<Object> getByQuery(@RequestBody DeliveryReadQueryRequest request) throws DeliveryNotFoundException {

        StringBuilder filter = new StringBuilder();
        filter.append(" 1=1 ");
        if (request.DeliveryAddressCity != null)
        {
            filter.append(String.format(" AND ANY address IN addresses SATISFIES address.city = '%s' END", request.DeliveryAddressCity));
        }
        if (request.DeliveryAddressTown != null)
        {
            filter.append(String.format(" AND ANY address IN addresses SATISFIES address.town = '%s' END", request.DeliveryAddressTown));
        }

        if (request.DeliveryTrackingNumber != null)
        {
            filter.append(String.format(" AND ANY package IN packages SATISFIES package.trackingNumber = '%s' END", request.DeliveryTrackingNumber));
        }

        if (request.CompanyName != null)
        {
            filter.append(String.format(" AND company.name = '%s'", request.CompanyName));
        }

        if (request.BeginDeliveryDate != null)
        {
            filter.append(String.format(" AND creationDate > %s", request.BeginDeliveryDate));
        }

        if (request.EndDeliveryDate != null)
        {
            filter.append(String.format(" AND creationDate < %s", request.EndDeliveryDate));
        }

        Flux<Delivery> deliveryFlux = deliveryRepository.findDeliveriesByCompanyAndCustomField(filter.toString() );
        if (deliveryFlux.hasElements().block())
        {
            return ResponseEntity.ok(deliveryFlux.collectList().block());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No record found");

    }
}
