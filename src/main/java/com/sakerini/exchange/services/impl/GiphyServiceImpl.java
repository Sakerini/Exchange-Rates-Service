package com.sakerini.exchange.services.impl;

import com.sakerini.exchange.api.GiphyApi;
import com.sakerini.exchange.exceptions.GifNotFoundException;
import com.sakerini.exchange.models.GiphyDTO;
import com.sakerini.exchange.models.responses.GiphyResponse;
import com.sakerini.exchange.services.GiphyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class GiphyServiceImpl implements GiphyService {

    private final int LIMIT = 50;

    @Autowired
    private GiphyApi giphyApi;
    @Value("${giphy.service.key}")
    private String apiKey;

    @Override
    public List<GiphyDTO> getGifs(String search, int limit) {
        log.info("Inside GiphyService method getGifs");
        GiphyResponse gifs = giphyApi.getGifs(apiKey, search, limit);
        return gifs.getData();
    }

    @Override
    public GiphyDTO getRandomGif(String search) throws GifNotFoundException {
        log.info("Inside GiphyService method getRandomGif");

        List<GiphyDTO> gifs = getGifs(search, LIMIT);
        if (CollectionUtils.isEmpty(gifs)) {
            log.error("Gif Service not Available. gifs are empty or null");
            throw new GifNotFoundException("code-3", "Gif Service not Available. gifs are empty");
        }

        Random random = new Random();
        return gifs.get(random.nextInt(gifs.size()));

    }
}
