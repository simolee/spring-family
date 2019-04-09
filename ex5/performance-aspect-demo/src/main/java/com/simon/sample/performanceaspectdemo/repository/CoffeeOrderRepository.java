package com.simon.sample.performanceaspectdemo.repository;

import com.simon.sample.performanceaspectdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
