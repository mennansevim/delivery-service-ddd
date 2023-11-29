package com.global.delivery.controller;

import com.global.delivery.application.controller.delivery.DeliveryController;
import com.global.delivery.application.controller.delivery.requests.CreateDeliveryRequest;
import com.global.delivery.domain.address.AddressType;
import com.global.delivery.domain.company.Company;
import com.global.delivery.domain.delivery.Delivery;
import com.global.delivery.domain.services.CompanyService;
import com.global.delivery.domain.services.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeliveryControllerTest {

    private DeliveryController deliveryController;
    private DeliveryService deliveryService;
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        deliveryService = mock(DeliveryService.class);
        companyService = mock(CompanyService.class);
        deliveryController = new DeliveryController(deliveryService, companyService);
    }

    @Test
    void testCreateDelivery_Success() {
        CreateDeliveryRequest request = createDummyDeliveryRequest();
        request.company.createdBy = "";

        Delivery domainDelivery = mock(Delivery.class);

        when(companyService.getByIdentityNumber(anyString()))
                .thenReturn(Flux.just(mock(Company.class)));

        when(deliveryService.saveDelivery(any(Delivery.class)))
                .thenReturn(Mono.just(domainDelivery));

        ResponseEntity<Object> response = deliveryController.createDelivery(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(domainDelivery, response.getBody());
    }

    public static CreateDeliveryRequest createDummyDeliveryRequest() {
        CreateDeliveryRequest deliveryRequest = new CreateDeliveryRequest();

        deliveryRequest.company = new CreateDeliveryRequest.RequestCompany();
        deliveryRequest.company.code = "ECOMMERCE-X";
        deliveryRequest.company.name = "X Company";
        deliveryRequest.company.identityNumber = "X001";
        deliveryRequest.company.createdBy = "CompanyOwner";

        deliveryRequest.companyAddress = new CreateDeliveryRequest.RequestAddress();
        deliveryRequest.companyAddress.AddressType = AddressType.CompanyWarehouse;
        deliveryRequest.companyAddress.Address = "Company Warehouse Address";
        deliveryRequest.companyAddress.FirstName = "Company";
        deliveryRequest.companyAddress.LastName = "Owner";
        deliveryRequest.companyAddress.Email = "company.owner@example.com";
        deliveryRequest.companyAddress.City = "ISTANBUL";
        deliveryRequest.companyAddress.Town = "SARIYER";
        deliveryRequest.companyAddress.CountryName = "TURKIYE";
        deliveryRequest.companyAddress.Phone = "98761233210";
        deliveryRequest.companyAddress.FreeTradeZone = true;

        deliveryRequest.shippingAddress = new CreateDeliveryRequest.RequestAddress();
        deliveryRequest.shippingAddress.AddressType = AddressType.Shipping;
        deliveryRequest.shippingAddress.Address = "Shipping Address";
        deliveryRequest.shippingAddress.FirstName = "Mennan";
        deliveryRequest.shippingAddress.LastName = "Sevim";
        deliveryRequest.shippingAddress.Email = "mennan.sevim@example.com";
        deliveryRequest.shippingAddress.City = "IZMIR";
        deliveryRequest.shippingAddress.Town = "SEFERIHISAR";
        deliveryRequest.shippingAddress.CountryName = "TURKIYE";
        deliveryRequest.shippingAddress.Phone = "9876543210";
        deliveryRequest.shippingAddress.FreeTradeZone = true;

        deliveryRequest.packages = new ArrayList<>();
        CreateDeliveryRequest.RequestPackage requestPackage = new CreateDeliveryRequest.RequestPackage();
        requestPackage.name = "T-Shirt";
        requestPackage.contentId = 2812361;
        requestPackage.barcode = "TH102391231";
        requestPackage.quantity = 10;

        requestPackage.items = new ArrayList<>();
        CreateDeliveryRequest.RequestPackageItem packageItem1 = new CreateDeliveryRequest.RequestPackageItem();
        packageItem1.color = "Red";
        packageItem1.size = "Medium";
        packageItem1.variantId = 13124124;
        requestPackage.items.add(packageItem1);

        CreateDeliveryRequest.RequestPackageItem packageItem2 = new CreateDeliveryRequest.RequestPackageItem();
        packageItem2.color = "Blue";
        packageItem2.size = "Large";
        packageItem2.variantId = 1234123;
        requestPackage.items.add(packageItem2);

        deliveryRequest.packages.add(requestPackage);

        return deliveryRequest;
    }

    @Test
    void testCreateDelivery_CompanyNotFound() {
        CreateDeliveryRequest request = createDummyDeliveryRequest();

        when(companyService.getByIdentityNumber(anyString()))
                .thenReturn(Flux.empty());

        ResponseEntity<Object> response = deliveryController.createDelivery(request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Company with identity number " + request.company.identityNumber + " not found.", response.getBody());
    }
}
