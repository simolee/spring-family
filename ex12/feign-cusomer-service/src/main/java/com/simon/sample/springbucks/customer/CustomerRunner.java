package com.simon.sample.springbucks.customer;

import com.simon.sample.springbucks.customer.integration.CoffeeOrderService;
import com.simon.sample.springbucks.customer.integration.CoffeeService;
import com.simon.sample.springbucks.customer.model.Coffee;
import com.simon.sample.springbucks.customer.model.CoffeeOrder;
import com.simon.sample.springbucks.customer.model.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
@Slf4j
@Component
public class CustomerRunner implements ApplicationRunner {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readMenu();
        Long id = orderCoffee();
        queryOrder(id);
    }

    private void readMenu() {
        List<Coffee> coffees = coffeeService.getAll();
        coffees.forEach( c-> log.info("Coffee: {}", c));
    }

    private Long orderCoffee() {
        OrderVO orderVO = OrderVO.builder()
                .customer("Li Lei")
                .items(Arrays.asList("capuccino"))
                .build();
        CoffeeOrder order = orderService.create(orderVO);
        log.info("Order ID: {}", order.getId());
        return order.getId();
    }

    private void queryOrder(Long id) {
        CoffeeOrder order = orderService.getOrder(id);
        log.info("Order: {}", order);
    }


}
