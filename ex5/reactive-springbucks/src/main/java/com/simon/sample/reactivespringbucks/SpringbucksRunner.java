package com.simon.sample.reactivespringbucks;

import com.simon.sample.reactivespringbucks.model.Coffee;
import com.simon.sample.reactivespringbucks.model.CoffeeOrder;
import com.simon.sample.reactivespringbucks.model.OrderState;
import com.simon.sample.reactivespringbucks.service.CoffeeService;
import com.simon.sample.reactivespringbucks.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Slf4j
@Component
public class SpringbucksRunner implements ApplicationRunner {

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        coffeeService.initCache()
                .then(coffeeService.findOneCoffee("mocha")
                        .flatMap(c -> {
                            CoffeeOrder order = createOrder("Li Lei", c);
                            return orderService.create(order);
                        })
                        .doOnError(t -> log.error("error", t)))
                .subscribe(o -> log.info("Create Order: {}", o));
        log.info("After Subscribe");
        Thread.sleep(5000);
    }

    private CoffeeOrder createOrder(String customer, Coffee... coffees) {
        return CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffees))
                .state(OrderState.INIT)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
    }
}
