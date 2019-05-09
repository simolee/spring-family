package com.simon.sample.springbucks.customer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class OrderVO {

    private String customer;
    private List<String> items;
}
