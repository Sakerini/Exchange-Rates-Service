package com.sakerini.exc.datasource.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatesDto {
    private String base;
    private Map<String, BigDecimal> rates;
}
