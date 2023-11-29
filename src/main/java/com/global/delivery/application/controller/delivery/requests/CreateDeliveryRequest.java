package com.global.delivery.application.controller.delivery.requests;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonProperty;
import com.global.delivery.domain.address.Address;
import com.global.delivery.domain.address.AddressType;
import com.global.delivery.domain.address.args.CreateAddressArg;
import com.global.delivery.domain.company.Company;
import com.global.delivery.domain.company.args.CreateCompanyArg;
import com.global.delivery.domain.delivery.Delivery;
import com.global.delivery.domain.delivery.args.CreateDeliveryArg;
import com.global.delivery.domain.packageitems.PackageItem;
import com.global.delivery.domain.packageitems.args.CreatePackageItemArg;
import com.global.delivery.domain.packages.Package;
import com.global.delivery.domain.packages.args.CreatePackageArg;
import com.global.delivery.domain.product.Product;
import com.global.delivery.domain.product.args.CreateProductArg;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.UUID;

public class CreateDeliveryRequest {
    @Valid
    @NotNull(message = "RequestCompany must not be null")
    @JsonProperty("Company")
    public RequestCompany company;
    @NotNull
    public RequestAddress companyAddress;

    @NotNull
    public RequestAddress shippingAddress;

    @Valid
    @Getter
    @Setter
    public List<RequestPackage> packages;

    public CreateDeliveryRequest() {
    }

    @Getter
    @Setter
    public static class RequestPackage {
        @NotBlank(message = "Name must not be blank")
        public String name;

        @Positive(message = "ContentId must be a positive integer")
        public int contentId;

        @NotBlank(message = "Barcode must not be blank")
        public String barcode;

        @NotNull(message = "Quantity must not be null")
        @Positive(message = "Quantity must be a positive integer")
        public Integer quantity;

        @Valid
        @NotNull(message = "RequestPackageItems must not be null")
        public List<RequestPackageItem> items;
    }

    public Delivery ToDelivery() {
        Company domainCompany = Company.create(
                new CreateCompanyArg(
                        company.code,
                        company.name,
                        company.identityNumber,
                        company.createdBy));

        Address companyAddress = Address.Create(new CreateAddressArg(
                AddressType.CompanyWarehouse,
                this.companyAddress.Address,
                this.companyAddress.FirstName,
                this.companyAddress.LastName,
                this.companyAddress.FirstName + " " + this.companyAddress.LastName,
                this.companyAddress.Email,
                this.companyAddress.City,
                this.companyAddress.Town,
                this.companyAddress.CountryName,
                this.companyAddress.Phone,
                this.companyAddress.FreeTradeZone));

        Address shippingAddress = Address.Create(new CreateAddressArg(
                AddressType.Shipping,
                this.shippingAddress.Address,
                this.shippingAddress.FirstName,
                this.shippingAddress.LastName,
                this.shippingAddress.FirstName + " " + this.shippingAddress.LastName,
                this.shippingAddress.Email,
                this.shippingAddress.City,
                this.shippingAddress.Town,
                this.shippingAddress.CountryName,
                this.shippingAddress.Phone,
                this.shippingAddress.FreeTradeZone));

        Delivery domainDelivery = Delivery.Create(new CreateDeliveryArg(domainCompany, companyAddress, shippingAddress));
        if (packages.stream().count() > 0) {
            for (RequestPackage pck : packages) {
                Product domainProduct = Product.Create(new CreateProductArg(pck.name, pck.barcode, pck.contentId, pck.quantity));
                Package domainPackage = Package.Create(new CreatePackageArg(domainProduct));

                // Set trackingNumber for query
                domainPackage.shipping(UUID.randomUUID().toString());

                for (RequestPackageItem item : pck.items) {
                    PackageItem domainPackageItem = PackageItem.Create(new CreatePackageItemArg(item.color, item.size, item.variantId));
                    domainPackage.addPackageItem(domainPackageItem);
                }

                domainDelivery.AddPackage(domainPackage);
            }
        }

        return domainDelivery;
    }

    public static class RequestCompany {

        public String code;

        public String name;

        @NotBlank(message = "IdentityNumber must not be blank")
        public String identityNumber;

        public String createdBy;

    }

    public static class RequestAddress {
        @NotNull(message = "AddressType must not be null")
        public AddressType AddressType;

        @NotBlank(message = "Address must not be blank")
        public String Address;

        @NotBlank(message = "FirstName must not be blank")
        public String FirstName;

        @NotBlank(message = "LastName must not be blank")
        public String LastName;

        @NotBlank(message = "Email must not be blank")
        public String Email;

        @NotBlank(message = "City must not be blank")
        public String City;

        @NotBlank(message = "Town must not be blank")
        public String Town;

        @NotBlank(message = "CountryName must not be blank")
        public String CountryName;

        @NotBlank(message = "CreatorEmail must not be blank")
        public String CreatorEmail;

        @NotBlank(message = "Phone must not be blank")
        @Pattern(regexp = "\\d{10}", message = "Phone must be a 10-digit number")
        public String Phone;

        public Boolean FreeTradeZone;
    }

    public static class RequestPackageItem {
        @NotBlank(message = "Color must not be blank")
        public String color;
        public String size;
        public int variantId;
    }
}


