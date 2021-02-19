package com.sakerini.exchange.services;

import java.math.BigDecimal;
import java.util.Map;

public interface OpenExchangeService {

    /**
     * This method should return all current currency rates
     *
     * @return map of current currency rates <Currency, Rate>
     */
    Map<String, BigDecimal> getAllCurrencies();

    /**
     * This method should return all yesterday currency rates
     *
     * @return map of yesterday currency rates <Currency, Rate>
     */
    Map<String, BigDecimal> getAllCurrenciesYesterday();

    /**
     * This method should return currency rate for particular currency
     *
     * @param currency
     * @return rate
     */
    BigDecimal getCurrencyRate(String currency);

    /**
     * This Method returns the difference ratio between current currency rate and yesterday
     *
     * @param currency
     * @return ratio difference
     */
    BigDecimal getChangeRatio(String currency);
}
