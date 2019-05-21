package com.simon.sample.spingbucks.waiter.repository;

import com.simon.sample.spingbucks.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
