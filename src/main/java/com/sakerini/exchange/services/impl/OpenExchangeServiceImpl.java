package com.sakerini.exchange.services.impl;

import com.sakerini.exchange.api.OpenExchangeApi;
import com.sakerini.exchange.models.RatesDTO;
import com.sakerini.exchange.services.OpenExchangeService;
import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class OpenExchangeServiceImpl implements OpenExchangeService {

    private ConcurrentHashMap<String, BigDecimal> rates = new ConcurrentHashMap<>();

    private final OpenExchangeApi openExchangeApi;

    @Value("${openExchange.service.key}")
    private String openExchangeKey;

    @Value("${openExchange.service.currency}")
    private String baseCurrency;

    public OpenExchangeServiceImpl(OpenExchangeApi openExchangeApi) {
        this.openExchangeApi = openExchangeApi;
    }

    @Override
    public Map<String, BigDecimal> getAllCurrencies() {
        log.info("Inside OpenExchangeService getAllCurrencies calling api getAllCurrencies");
        RatesDTO ratesDto;
        try {
            ratesDto = openExchangeApi.getAllCurrencies(openExchangeKey, baseCurrency);
        } catch (RetryableException exc) {
            log.error("Exception: {} Returned empty list", exc.getMessage());
            return new HashMap<>();
        }
        return ratesDto.getRates();
    }

    @Override
    public BigDecimal getCurrencyRate(String currency) {
        log.info("Inside getCurrencyRate");
        RatesDTO ratesDto = openExchangeApi.getAllCurrencies(openExchangeKey, baseCurrency);
        return ratesDto.getRates().get(currency);
    }

    @Override
    public BigDecimal getChangeRatio(String currency) {
        log.info("Inside getChangeRatio");

        BigDecimal newRate = getCurrencyRate(currency);
        if (newRate == null) {
            log.error("Could not get new Ratio");
            return null;
        }
        BigDecimal oldRate = rates.get(currency);
        if (oldRate == null) {
            log.error("Could not get old Ratio");
            return null;
        }
        return newRate.subtract(oldRate);
    }


    // one day
    @Scheduled(fixedRate = 86400000)
    public void updateCurrencies() {
        log.info("Updating Currencies");
        Map<String, BigDecimal> resultRates = getAllCurrencies();
        rates.putAll(resultRates);
    }
}
