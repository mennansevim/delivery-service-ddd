package com.global.delivery.application.controller.company.requests;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class CompanyReadQueryRequest {
    @Nullable
    public Long BeginDeliveryDate;
    @Nullable
    public Long EndDeliveryDate;
    @Nullable
    public String Code;
    @Nullable
    public String CreatedBy;
    @Nullable
    public String IdentityNumber;
    @Nullable
    public String Name;
}
