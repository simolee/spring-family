package com.simon.sample.springbucks.waiter.support;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class CoffeeIndicator implements HealthIndicator {

    @Override
    public Health health() {
        return null;
    }
}
