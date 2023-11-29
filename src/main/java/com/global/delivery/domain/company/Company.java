package com.global.delivery.domain.company;

import com.global.delivery.domain.BaseEntity;
import com.global.delivery.domain.ISoftDelete;
import com.global.delivery.domain.company.args.CreateCompanyArg;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.repository.Collection;

@Getter
@Document
@Collection("Company")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Company extends BaseEntity implements ISoftDelete {
    private String code;
    private String name;
    private String identityNumber;
    private String createdBy;

    public static Company create(CreateCompanyArg companyArg) {
        return new Company(
                companyArg.getCode(),
                companyArg.getName(),
                companyArg.getIdentityNumber(),
                companyArg.getCreatedBy()
        );
    }
}