package com.simon.sample.springbucks.waiter.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
public class CoffeeVO {

    @NotBlank
    private String name;

    @NotNull
    private Money price;

}
