package com.sakerini.exchange.services;

import com.sakerini.exchange.models.GiphyDTO;
import com.sakerini.exchange.exceptions.GifNotFoundException;

import java.util.List;

public interface GiphyService {
    List<GiphyDTO> getGifs(String search, int limit);

    GiphyDTO getRandomGif(String search) throws GifNotFoundException;
}
