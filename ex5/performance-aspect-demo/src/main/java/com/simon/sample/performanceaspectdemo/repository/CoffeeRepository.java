package com.simon.sample.performanceaspectdemo.repository;

import com.simon.sample.performanceaspectdemo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
