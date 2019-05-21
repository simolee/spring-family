package com.simon.sample.springbucks.customer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.simon.sample.springbucks.customer.integration.CoffeeOrderService;
import com.simon.sample.springbucks.customer.integration.CoffeeService;
import com.simon.sample.springbucks.customer.model.Coffee;
import com.simon.sample.springbucks.customer.model.CoffeeOrder;
import com.simon.sample.springbucks.customer.model.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService orderService;

    @GetMapping("/menu")
    public List<Coffee> readMenu() {
        List<Coffee> list = coffeeService.getAll();
        log.info("Read Menu: {} coffee", list.size());
        return list;
    }

    @PostMapping("/order")
    @HystrixCommand(fallbackMethod = "fallbackCreateOrder")
    public CoffeeOrder createOrder() {
        OrderVO orderVO = OrderVO.builder()
                .customer("Li Lei")
                .items(Arrays.asList("capuccino"))
                .build();
        CoffeeOrder order = orderService.create(orderVO);
        log.info("Order ID: {}", order != null ? order.getId() : "-");
        return order;
    }

    public CoffeeOrder fallbackCreateOrder() {
        log.warn("Fallback to NULL order.");
        return null;
    }

}
