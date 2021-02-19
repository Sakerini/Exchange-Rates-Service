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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OpenExchangeServiceTests {

    @MockBean(name = "${openExchange.service.name}")
    OpenExchangeApi openExchangeApi;

    @Autowired
    private OpenExchangeService oxcService;

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
        Map<String, BigDecimal> rates = oxcService.getAllCurrencies();

        assertThat(rates.equals(mockedRates));
    }

    @Test
    void testGetCurrencyRate() {
        Map<String, BigDecimal> mockedRates = new HashMap<>();
        mockedRates.put("RUB", new BigDecimal(73.9714));
        mockedRates.put("RWF", new BigDecimal(990));
        mockedRates.put("SDG", new BigDecimal(55.25));

        Mockito.when(openExchangeApi.getAllCurrencies(apiKey, base)).thenReturn(new RatesDTO("USD", mockedRates));
        BigDecimal rubRate = oxcService.getCurrencyRate("RUB");
        BigDecimal rwfRate = oxcService.getCurrencyRate("RWF");
        BigDecimal sdgRate = oxcService.getCurrencyRate("SDG");

        assertThat(rubRate.compareTo(new BigDecimal(73.9714)));
        assertThat(rwfRate.compareTo(new BigDecimal(990)));
        assertThat(sdgRate.compareTo(new BigDecimal(55.25)));
    }

    @Test
    void testGetCurrencyDiff() {
        Map<String, BigDecimal> mockedRates = new HashMap<>();
        mockedRates.put("RUB", new BigDecimal(73.9714));
        mockedRates.put("RWF", new BigDecimal(990));
        mockedRates.put("SCR", new BigDecimal(21.205927));
        mockedRates.put("SDG", new BigDecimal(55.25));

        Mockito.when(openExchangeApi.getAllCurrencies(apiKey, base)).thenReturn(new RatesDTO("USD", mockedRates));
        BigDecimal rubRate = oxcService.getChangeRatio("RUB");

        assertThat(rubRate.compareTo(new BigDecimal(73.9714)));
    }
}
