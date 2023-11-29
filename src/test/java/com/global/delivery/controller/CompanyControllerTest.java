package com.global.delivery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.delivery.application.controller.company.CompanyController;
import com.global.delivery.application.controller.company.requests.CreateCompanyRequest;
import com.global.delivery.domain.company.Company;
import com.global.delivery.domain.company.args.CreateCompanyArg;
import com.global.delivery.domain.services.CompanyService;
import com.global.delivery.infrastructure.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;
    @MockBean
    private CompanyRepository companyRepository;

    @Test
    public void testCreateCompany_Success() throws Exception {
        String companyIdentityNumber = "COMPANY123";
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest();
        createCompanyRequest.identityNumber = companyIdentityNumber;

        Company expectedCompany = Company.create(new CreateCompanyArg("", "", companyIdentityNumber, ""));

        when(companyService.getByIdentityNumber(Mockito.anyString()))
                .thenReturn(Flux.empty());
        when(companyService.saveCompany(any()))
                .thenReturn(Mono.just(expectedCompany));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createCompanyRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.identityNumber").value(companyIdentityNumber));
    }

    @Test
    public void testCreateCompany_CompanyExists() throws Exception {
        CreateCompanyRequest createCompanyRequest = new CreateCompanyRequest();
        String companyIdentityNumber = "COMPANY123";
        createCompanyRequest.identityNumber = companyIdentityNumber;

        Company existingCompany = Company.create(new CreateCompanyArg("", "", companyIdentityNumber, ""));

        when(companyService.getByIdentityNumber(Mockito.anyString()))
                .thenReturn(Flux.just(existingCompany));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(createCompanyRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.identityNumber").value(companyIdentityNumber));
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
