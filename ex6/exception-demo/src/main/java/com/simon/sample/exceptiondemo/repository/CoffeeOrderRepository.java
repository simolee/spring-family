package com.simon.sample.exceptiondemo.repository;

import com.simon.sample.exceptiondemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
