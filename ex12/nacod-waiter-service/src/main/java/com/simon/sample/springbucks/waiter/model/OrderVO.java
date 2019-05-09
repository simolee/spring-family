package com.simon.sample.springbucks.waiter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderVO {

    private String customer;

    private List<String> items;

}
