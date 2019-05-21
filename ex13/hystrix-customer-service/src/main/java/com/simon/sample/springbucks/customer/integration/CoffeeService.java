package com.simon.sample.springbucks.customer.integration;

import com.simon.sample.springbucks.customer.model.Coffee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "waiter-service", contextId = "coffee",
        qualifier = "coffeeService", path = "/coffee", fallback = FallbackCoffeeService.class)
public interface CoffeeService {

    @GetMapping(path = "/", params = "!name")
    List<Coffee> getAll();

    @GetMapping("/{id}")
    Coffee getById(@PathVariable Long id);

    @GetMapping(path = "/", params = "name")
    Coffee getByName(@RequestParam String name);

}
