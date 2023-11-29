package com.global.delivery.domain.company.args;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
public class CreateCompanyArg{
    protected String code;
    protected String name;
    protected String IdentityNumber;
    protected String createdBy;
}

