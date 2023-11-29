package com.global.delivery.domain.address.args;

import com.global.delivery.domain.address.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAddressArg {
    private AddressType addressType;
    private String address;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String city;
    private String district;
    private String countryName;
    private String phone;
    private Boolean freeTradeZone;
}

