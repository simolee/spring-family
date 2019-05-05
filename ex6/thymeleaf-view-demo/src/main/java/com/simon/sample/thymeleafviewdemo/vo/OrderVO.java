package com.simon.sample.thymeleafviewdemo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter
@Getter
@ToString
public class OrderVO {

    @NotEmpty
    private String customer;

    @NotEmpty
    private List<String> items;

}
