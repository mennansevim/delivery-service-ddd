package com.global.delivery.application.controller.company.requests;


import com.global.delivery.domain.company.Company;
import com.global.delivery.domain.company.args.CreateCompanyArg;


public class CreateCompanyRequest {
   
    public String code;
   
    public String name;
    public String createdBy;

    public String identityNumber;
    public Company ToCompany(){
        return Company.create(new CreateCompanyArg(code,name, identityNumber,createdBy));
    }
}