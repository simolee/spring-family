package com.simon.sample.exceptiondemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class OrderVO {

    private String customer;

    private List<String> items;

}
