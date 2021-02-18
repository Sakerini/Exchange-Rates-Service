package com.sakerini.exc.datasource.service.impl;

import com.sakerini.exc.datasource.api.FeignOxcApi;
import com.sakerini.exc.datasource.entity.RatesDto;
import com.sakerini.exc.datasource.service.OxcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class OxcServiceImpl implements OxcService {

    private ConcurrentHashMap<String, BigDecimal> rates = new ConcurrentHashMap<>();

    @Autowired
    private FeignOxcApi oxcApi;

    @Value("${oxc.service.key}")
    private String oxcKey;

    @Override
    public Map<String, BigDecimal> getAllCurrencies() {
        log.info("Inside OxcService getAllCurrencies calling api getAllCurrencies");
        RatesDto ratesDto;
        try {
            ratesDto = oxcApi.getAllCurrencies(oxcKey);
        } catch (Exception exc) {
            log.info("Exception: " + exc.toString() + " Returned empty list");
            return new HashMap();
        }
        return ratesDto.getRates();
    }

    @Override
    public BigDecimal getCurrencyRate(String currency) {
        log.info("Inside getCurrencyRate");
        RatesDto ratesDto;
        try {
            ratesDto = oxcApi.getAllCurrencies(oxcKey);
        } catch (Exception exc) {
            log.info("Exception: " + exc.toString() + " Returned null");
            return null;
        }
        return ratesDto.getRates().get(currency);
    }

    @Override
    public BigDecimal getChangeRatio(String currency) {
        BigDecimal newRate = getCurrencyRate(currency);
        if (newRate == null) {
            return null;
        }
        BigDecimal oldRate = rates.get(currency);
        if (oldRate == null) {
            return null;
        }
        return newRate.subtract(oldRate);
    }


    @Scheduled(fixedRate = 86400000)
    public void updateCurrencies() {
        log.info("Updating Currencies");
        Map<String, BigDecimal> resultRates = getAllCurrencies();
        rates.putAll(resultRates);
    }
}
