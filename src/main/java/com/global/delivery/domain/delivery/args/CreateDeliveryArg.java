package com.global.delivery.domain.delivery.args;

import com.global.delivery.domain.address.Address;
import com.global.delivery.domain.company.Company;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateDeliveryArg {
    private Company company;
    private Address companyAddress;
    private Address shippingAddress;
}
