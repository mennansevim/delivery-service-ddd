package com.global.delivery.infrastructure.repository;

import com.global.delivery.domain.company.Company;
import com.global.delivery.domain.delivery.Delivery;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;


@Repository // CouchbasePagingAndSortingRepository
public interface CompanyRepository extends ReactiveCouchbaseRepository<Company, String> {
    @Query ("#{#n1ql.selectEntity} WHERE #{[0]}")
    Flux<List<Company>> findCompaniesByCompanyAndCustomField(String n1qlWhere);
    Flux<Company> findCompanyByIdentityNumber(String identityNumber);
}
