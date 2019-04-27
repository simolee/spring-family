package com.simon.sample.springbucks.waiter.service;

import com.simon.sample.springbucks.waiter.model.Coffee;
import com.simon.sample.springbucks.waiter.model.CoffeeOrder;
import com.simon.sample.springbucks.waiter.model.OrderState;
import com.simon.sample.springbucks.waiter.repository.CoffeeOrderRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
@Transactional
public class CoffeeOrderService implements MeterBinder {

    @Autowired
    private CoffeeOrderRepository orderRepository;

    private Counter orderCounter = null;

    public CoffeeOrder get(Long id) {
        return orderRepository.getOne(id);
    }

    public CoffeeOrder createOrder(String customer, Coffee... coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = orderRepository.save(order);
        log.info("New order: {}", saved);
        orderCounter.increment();
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        orderRepository.save(order);
        log.info("Updated Order: {}", order);
        return true;
    }

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        this.orderCounter = meterRegistry.counter("order.count");
    }

}
