package com.sakerini.exchange.api;

import com.sakerini.exchange.models.RatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${openExchange.service.name}", url = "${openExchange.service.base.url}")
public interface OpenExchangeApi {
    @GetMapping("/api/latest.json")
    RatesDTO getAllCurrencies(@RequestParam(value = "app_id") String appId,
                              @RequestParam(value = "base") String base);
}
