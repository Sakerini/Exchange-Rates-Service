package com.sakerini.exc.datasource.api;

import com.sakerini.exc.datasource.entity.GiphyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${giphy.service.name}", url = "${giphy.service.base.url}")
public interface FeignGiphyApi {

    @GetMapping("/v1/gifs/search")
    List<GiphyDto> getGifs(@RequestParam(value = "api_key") String apiKey,
                           @RequestParam(value = "q") String q,
                           @RequestParam(value = "limit") int limit);
}
