package com.simon.sample.mongodbdemo.converter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;

public class MoneyWriteConverter implements Converter<Money, Long> {

    @Override
    public Long convert(Money money) {
        return money.getAmountMinorLong();
    }
}
