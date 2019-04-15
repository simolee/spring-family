package com.simon.r2dbcrepositorydemo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("t_coffee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {

    @Id
    private Long id;

    private String name;

    private Money price;

    private Date createTime;

    private Date updateTime;


}
