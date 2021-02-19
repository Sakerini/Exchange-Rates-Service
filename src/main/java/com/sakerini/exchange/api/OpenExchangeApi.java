package com.sakerini.exchange.api;

import com.sakerini.exchange.models.RatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${openExchange.service.name}", url = "${openExchange.service.base.url}")
public interface OpenExchangeApi {

    /**
     * This Method calls Open Exhcange Currency API https://docs.openexchangerates.org/
     * it requests all current currency rates.
     *
     * @param appId Your unique App ID
     * @param base  Change base currency (3-letter code, default: USD)
     * @return a Data transfer object of base and current rates
     */
    @GetMapping("/api/latest.json")
    RatesDTO getAllCurrencies(@RequestParam(value = "app_id") String appId,
                              @RequestParam(value = "base") String base);

    /**
     * This Method calls Open Exhcange Currency API https://docs.openexchangerates.org/
     * it requests all currency rates by date
     *
     * @param date  date in format yyyy-MM-dd
     * @param appId Your unique App ID
     * @param base  Change base currency (3-letter code, default: USD)
     * @return
     */
    @GetMapping("/api/historical/{date}.json")
    RatesDTO getAllCurrenciesByDate(@PathVariable(name = "date") String date,
                                    @RequestParam(value = "app_id") String appId,
                                    @RequestParam(value = "base") String base);
}
