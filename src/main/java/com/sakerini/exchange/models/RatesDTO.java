package com.sakerini.exchange.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatesDTO {

    @JsonProperty("base")
    private String base;

    @JsonProperty("rates")
    private Map<String, BigDecimal> rates;
}
