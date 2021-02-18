package com.sakerini.exc.datasource.service;

import java.math.BigDecimal;
import java.util.Map;

public interface OxcService {
    Map<String, BigDecimal> getAllCurrencies();
    BigDecimal getCurrencyRate(String currency);
    BigDecimal getChangeRatio(String currency);
}
