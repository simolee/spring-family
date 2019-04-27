package com.simon.sample.springbucks.waiter.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class MoneyFormatter implements Formatter<Money> {

    @Override
    public Money parse(String s, Locale locale) throws ParseException {
        if (NumberUtils.isParsable(s)) {
            return Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(s));
        } else if (StringUtils.isNotEmpty(s)) {
            String[] moneyArr = StringUtils.split(s, " ");
            if (moneyArr != null && moneyArr.length == 2 && NumberUtils.isParsable(moneyArr[1])) {
                return Money.of(CurrencyUnit.of(moneyArr[0]), NumberUtils.createBigDecimal(moneyArr[1]));
            }
        }
        throw new ParseException(s, 0);
    }

    @Override
    public String print(Money money, Locale locale) {
        return money == null ? null : money.getCurrencyUnit().getCode() + " " + money.getAmount();
    }
}
