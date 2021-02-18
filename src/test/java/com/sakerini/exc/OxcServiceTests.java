package com.sakerini.exc;

import com.sakerini.exc.datasource.api.FeignOxcApi;
import com.sakerini.exc.datasource.entity.RatesDto;
import com.sakerini.exc.datasource.service.OxcService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
public class OxcServiceTests {

    @MockBean(name = "${oxc.service.name}")
    FeignOxcApi oxcApi;

    @Autowired
    private OxcService oxcService;

    @Value("${oxc.service.key}")
    private String apiKey;

    @Test
    void testGetAllCurrencies() {
        Map<String, BigDecimal> mockedRates = new HashMap<>();
        mockedRates.put("RUB", new BigDecimal(73.9714));
        mockedRates.put("RWF", new BigDecimal(990));
        mockedRates.put("SCR", new BigDecimal(21.205927));
        mockedRates.put("SDG", new BigDecimal(55.25));

        Mockito.when(oxcApi.getAllCurrencies(apiKey)).thenReturn(new RatesDto("USD", mockedRates));
        Map<String, BigDecimal> rates = oxcService.getAllCurrencies();

        assertThat(rates.equals(mockedRates));
    }

    @Test
    void testGetCurrencyRate() {
        Map<String, BigDecimal> mockedRates = new HashMap<>();
        mockedRates.put("RUB", new BigDecimal(73.9714));
        mockedRates.put("RWF", new BigDecimal(990));
        mockedRates.put("SDG", new BigDecimal(55.25));

        Mockito.when(oxcApi.getAllCurrencies(apiKey)).thenReturn(new RatesDto("USD", mockedRates));
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

        Mockito.when(oxcApi.getAllCurrencies(apiKey)).thenReturn(new RatesDto("USD", mockedRates));
        BigDecimal rubRate = oxcService.getChangeRatio("RUB");

        assertThat(rubRate.compareTo(new BigDecimal(73.9714)));
    }
}
