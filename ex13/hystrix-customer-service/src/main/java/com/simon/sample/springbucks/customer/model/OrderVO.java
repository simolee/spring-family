package com.simon.sample.springbucks.customer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class OrderVO {

    private String customer;

    private List<String> items;

}
