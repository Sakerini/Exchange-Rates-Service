package com.sakerini.exchange.api;

import com.sakerini.exchange.models.responses.GiphyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${giphy.service.name}", url = "${giphy.service.base.url}")
public interface GiphyApi {

    /**
     * This method calls Giphy's Api https://developers.giphy.com/docs/api/endpoint/
     * and returns gifs
     *
     * @param apiKey GIPHY API Key.
     * @param q Search query term or phrase.
     * @param limit The maximum number of objects to return.
     * @return an object with gifs
     */
    @GetMapping("/v1/gifs/search")
    GiphyResponse getGifs(@RequestParam(value = "api_key") String apiKey,
                          @RequestParam(value = "q") String q,
                          @RequestParam(value = "limit") int limit);
}
