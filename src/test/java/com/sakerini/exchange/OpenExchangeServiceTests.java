package com.sakerini.exchange;

import com.sakerini.exchange.api.OpenExchangeApi;
import com.sakerini.exchange.models.RatesDTO;
import com.sakerini.exchange.services.OpenExchangeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
public class OpenExchangeServiceTests {

    @MockBean
    OpenExchangeApi openExchangeApi;

    @Autowired
    private OpenExchangeService openExchangeService;

    @Value("${openExchange.service.key}")
    private String apiKey;

    @Value("${openExchange.service.currency}")
    private String base;

    @Test
    void testGetAllCurrencies() {
        Map<String, BigDecimal> mockedRates = new HashMap<>();
        mockedRates.put("RUB", new BigDecimal(73.9714));
        mockedRates.put("RWF", new BigDecimal(990));
        mockedRates.put("SCR", new BigDecimal(21.205927));
        mockedRates.put("SDG", new BigDecimal(55.25));

        Mockito.when(openExchangeApi.getAllCurrencies(apiKey, base)).thenReturn(new RatesDTO(base, mockedRates));
        Map<String, BigDecimal> rates = openExchangeService.getAllCurrencies();

        Assert.isTrue(rates.equals(mockedRates), "rates should be equal to mockedRates");
    }

    @Test
    void testGetCurrencyRate() {
        Map<String, BigDecimal> mockedRates = new HashMap<>();
        mockedRates.put("RUB", new BigDecimal(73.9714));
        mockedRates.put("RWF", new BigDecimal(990));
        mockedRates.put("SDG", new BigDecimal(55.25));

        Mockito.when(openExchangeApi.getAllCurrencies(apiKey, base)).thenReturn(new RatesDTO("USD", mockedRates));
        BigDecimal rubRate = openExchangeService.getCurrencyRate("RUB");
        BigDecimal rwfRate = openExchangeService.getCurrencyRate("RWF");
        BigDecimal sdgRate = openExchangeService.getCurrencyRate("SDG");

        Assert.isTrue(
                rubRate.compareTo(new BigDecimal(73.9714)) == 0,
                "Rate should be equal to mocked rate");
        Assert.isTrue(
                rwfRate.compareTo(new BigDecimal(990)) == 0,
                "Rate should be equal to mocked rate");
        Assert.isTrue(
                sdgRate.compareTo(new BigDecimal(55.25)) == 0,
                "Rate should be equal to mocked rate");
    }

    @Test
    void testGetCurrencyDiff() {
        Map<String, BigDecimal> mockedRates = new HashMap<>();
        mockedRates.put("RUB", new BigDecimal(73.30));
        mockedRates.put("RWF", new BigDecimal(990));
        mockedRates.put("SCR", new BigDecimal(21.20));
        mockedRates.put("SDG", new BigDecimal(55));

        ConcurrentHashMap<String, BigDecimal> mockedOldRates = new ConcurrentHashMap<>();
        mockedOldRates.put("RUB", new BigDecimal(73.30));
        mockedOldRates.put("RWF", new BigDecimal(995));
        mockedOldRates.put("SCR", new BigDecimal(20.20));
        mockedOldRates.put("SDG", new BigDecimal(45));

        Field field = ReflectionUtils.findField(openExchangeService.getClass(), "rates");
        field.setAccessible(true);
        ReflectionUtils.setField(field, openExchangeService, mockedOldRates);
        Mockito.when(openExchangeApi.getAllCurrencies(apiKey, base)).thenReturn(new RatesDTO(base, mockedRates));
        BigDecimal rubRate = openExchangeService.getChangeRatio("RUB");
        BigDecimal rwfRate = openExchangeService.getChangeRatio("RWF");
        BigDecimal scrRate = openExchangeService.getChangeRatio("SCR");
        BigDecimal sdgRate = openExchangeService.getChangeRatio("SDG");

        Assert.isTrue(
                rubRate.compareTo(new BigDecimal(0)) == 0,
                "Should be equal because ruble didnt change");
        Assert.isTrue(
                rwfRate.compareTo(new BigDecimal(0)) < 0,
                "Should be equal because ruble didnt change");
        Assert.isTrue(
                scrRate.compareTo(new BigDecimal(0)) > 0,
                "Should be equal because ruble didnt change");
        Assert.isTrue(
                sdgRate.compareTo(new BigDecimal(0)) > 0,
                "Should be equal because ruble didnt change");
    }
}
