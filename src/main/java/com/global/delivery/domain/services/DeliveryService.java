package com.global.delivery.domain.services;

import com.global.delivery.domain.delivery.Delivery;
import com.global.delivery.infrastructure.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Mono<Delivery> saveDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }
}
