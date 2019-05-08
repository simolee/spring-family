package com.simon.sample.springbucks.waiter.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

@Setter
@Getter
@ToString
public class CoffeeVO {

    private String name;

    private Money price;
}
