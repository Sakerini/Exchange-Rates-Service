package com.sakerini.exchange.api;

import com.sakerini.exchange.models.responses.GiphyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${giphy.service.name}", url = "${giphy.service.base.url}")
public interface GiphyApi {

    @GetMapping("/v1/gifs/search")
    GiphyResponse getGifs(@RequestParam(value = "api_key") String apiKey,
                          @RequestParam(value = "q") String q,
                          @RequestParam(value = "limit") int limit);
}
