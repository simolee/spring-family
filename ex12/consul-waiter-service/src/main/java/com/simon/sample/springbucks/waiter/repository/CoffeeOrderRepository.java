package com.simon.sample.springbucks.waiter.repository;

import com.simon.sample.springbucks.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
