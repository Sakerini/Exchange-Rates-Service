package com.sakerini.exc.datasource.api;

import com.sakerini.exc.datasource.entity.RatesDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${oxc.service.name}", url = "${oxc.service.base.url}")
public interface FeignOxcApi {
    @GetMapping("/api/latest.json")
    RatesDto getAllCurrencies(@RequestParam(value = "app_id") String appId);
}
