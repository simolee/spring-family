package com.simon.sample.reactivespringbucks.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.simon.sample.reactivespringbucks.serializer.MoneyDeserializer;
import com.simon.sample.reactivespringbucks.serializer.MoneySerializer;
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

    @JsonSerialize(using = MoneySerializer.class)
    @JsonDeserialize(using = MoneyDeserializer.class)
    private Money price;

    private Date createTime;

    private Date updateTime;
}
