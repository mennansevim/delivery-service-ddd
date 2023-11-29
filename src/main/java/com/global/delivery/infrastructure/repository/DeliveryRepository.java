package com.global.delivery.infrastructure.repository;

import com.global.delivery.domain.delivery.Delivery;
import org.springframework.data.couchbase.core.query.N1QLQuery;

import org.springframework.data.couchbase.core.query.QueryCriteria;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface DeliveryRepository extends ReactiveCouchbaseRepository<Delivery, String> {

    @Query ("#{#n1ql.selectEntity} WHERE #{[0]}")
    Flux<Delivery> findDeliveriesByCompanyAndCustomField(String n1qlWhere);
}
