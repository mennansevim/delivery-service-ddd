package com.global.delivery.domain.address;

import com.global.delivery.domain.address.args.CreateAddressArg;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {

    private AddressType addressType;
    private String address;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String town;
    private String countryName;
    private String creatorEmail;
    private String phone;
    private String fullName;
    private Boolean freeTradeZone;

    public static Address Create(CreateAddressArg arg) {
        return new Address(
                arg.getAddressType(),
                arg.getAddress(),
                arg.getFirstName(),
                arg.getLastName(),
                arg.getEmail(),
                arg.getCity(),
                arg.getDistrict(),
                arg.getCountryName(),
                arg.getEmail(),
                arg.getPhone(),
                arg.getFullName(),
                arg.getFreeTradeZone()
        );
    }
}