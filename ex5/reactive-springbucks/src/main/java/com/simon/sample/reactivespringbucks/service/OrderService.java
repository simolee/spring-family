package com.simon.sample.reactivespringbucks.service;

import com.simon.sample.reactivespringbucks.model.CoffeeOrder;
import com.simon.sample.reactivespringbucks.repository.CoffeeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    @Autowired
    private CoffeeOrderRepository repository;

    public Mono<Long> create(CoffeeOrder order) {
        return repository.save(order);
    }




}
