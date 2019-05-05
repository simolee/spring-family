package com.simon.sample.thymeleafviewdemo.repository;

import com.simon.sample.thymeleafviewdemo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
