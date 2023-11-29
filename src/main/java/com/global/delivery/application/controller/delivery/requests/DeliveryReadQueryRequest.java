package com.global.delivery.application.controller.delivery.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class DeliveryReadQueryRequest {
    public Long BeginDeliveryDate;
    @Nullable
    public Long EndDeliveryDate;
    @Nullable
    public String CompanyName;
    @Nullable
    public String DeliveryAddressTown;
    @Nullable
    public String DeliveryAddressCity;
    @Nullable
    public String DeliveryTrackingNumber;
}
