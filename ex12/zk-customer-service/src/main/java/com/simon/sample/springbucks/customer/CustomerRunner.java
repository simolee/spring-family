package com.simon.sample.springbucks.customer;

import com.simon.sample.springbucks.customer.model.Coffee;
import com.simon.sample.springbucks.customer.model.CoffeeOrder;
import com.simon.sample.springbucks.customer.model.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class CustomerRunner implements ApplicationRunner {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        showServiceInstances();
        readMenu();
        Long id = orderCoffee();
        queryOrder(id);
    }

    private void showServiceInstances() {
        log.info("DiscoveryClient: {}", discoveryClient.getClass().getName());
        discoveryClient.getInstances("waiter-service").forEach(
                s -> {
                    log.info("Host: {}, Port: {}", s.getHost(), s.getPort());
                }
        );
    }

    private void readMenu() {
        ParameterizedTypeReference<List<Coffee>> ptr = new ParameterizedTypeReference<List<Coffee>>() {
        };
        ResponseEntity<List<Coffee>> list = restTemplate.exchange("http://waiter-service/coffee/",
                HttpMethod.GET, null, ptr);
        list.getBody().forEach(c -> log.info("Coffee: {}", c));
    }

    private Long orderCoffee() {
        OrderVO orderVO = OrderVO.builder()
                .customer("Li Lei")
                .items(Arrays.asList("capuccino"))
                .build();
        RequestEntity<OrderVO> request = RequestEntity
                .post(UriComponentsBuilder.fromUriString("http://waiter-service/order/").build().toUri())
                .body(orderVO);
        ResponseEntity<CoffeeOrder> res = restTemplate.exchange(request, CoffeeOrder.class);
        log.info("Order Request Status Code: {}", res.getStatusCode());
        Long id = res.getBody().getId();
        log.info("Order ID: {}", id);
        return id;
    }

    private void queryOrder(Long id) {
        CoffeeOrder order = restTemplate.getForObject("http://waiter-service/order/{id}",
                CoffeeOrder.class, id);
        log.info("Order: {}", order);
    }
}
