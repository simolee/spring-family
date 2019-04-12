package com.simon.sample.reactivespringbucks.repository;

import com.simon.sample.reactivespringbucks.model.Coffee;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.r2dbc.repository.query.Query;
import reactor.core.publisher.Mono;

public interface CoffeeRepository extends R2dbcRepository<Coffee, Long> {

    @Query("select * from t_coffee where name=$1")
    Mono<Coffee> findByName(String name);
}
