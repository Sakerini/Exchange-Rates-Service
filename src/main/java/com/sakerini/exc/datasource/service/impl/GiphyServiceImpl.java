package com.sakerini.exc.datasource.service.impl;

import com.sakerini.exc.datasource.api.FeignGiphyApi;
import com.sakerini.exc.datasource.entity.GiphyDto;
import com.sakerini.exc.datasource.service.GiphyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class GiphyServiceImpl implements GiphyService {

    private final int LIMIT = 50;

    @Autowired
    private FeignGiphyApi giphyApi;
    @Value("${giphy.service.key}")
    private String apiKey;

    @Override
    public List<GiphyDto> getGifs(String search, int limit) {
        log.info("Inside GiphyService method getGifs");
        List<GiphyDto> result;
        try {
            result = giphyApi.getGifs(apiKey, search, limit);
        } catch (Exception exc) {
            log.info("Exception: " + exc + " return empty list");
            return new ArrayList<>();
        }
        return result;
    }

    @Override
    public GiphyDto getRandomGiff(String search) {
        List<GiphyDto> gifs = getGifs(search, LIMIT);
        if (!gifs.isEmpty()) {
            Random random = new Random();
            return gifs.get(random.nextInt(gifs.size()));
        }
        return null;
    }
}
