package com.simon.sample.springbucks.waiter.controller;

import com.simon.sample.springbucks.waiter.model.Coffee;
import com.simon.sample.springbucks.waiter.model.CoffeeOrder;
import com.simon.sample.springbucks.waiter.service.CoffeeOrderService;
import com.simon.sample.springbucks.waiter.service.CoffeeService;
import com.simon.sample.springbucks.waiter.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Autowired
    private CoffeeOrderService orderService;
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable("id") Long id) {
        CoffeeOrder order = orderService.get(id);
        log.info("Get Order: {}", order);
        return order;
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody OrderVO newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[]{});
        log.info("length: {}", coffeeList.length);
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }
}
