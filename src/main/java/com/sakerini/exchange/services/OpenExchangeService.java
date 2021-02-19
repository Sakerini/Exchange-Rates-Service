package com.sakerini.exchange.services;

import java.math.BigDecimal;
import java.util.Map;

public interface OpenExchangeService {
    Map<String, BigDecimal> getAllCurrencies();

    BigDecimal getCurrencyRate(String currency);

    BigDecimal getChangeRatio(String currency);
}
