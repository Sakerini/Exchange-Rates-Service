package com.sakerini.exchange.services;

import com.sakerini.exchange.exceptions.GifNotFoundException;
import com.sakerini.exchange.models.GiphyDTO;

import java.util.List;

public interface GiphyService {

    /**
     * This method should return a list of gifs
     *
     * @param search Search query term or phrase.
     * @param limit  The maximum number of objects to return.
     * @return list of gifs
     */
    List<GiphyDTO> getGifs(String search, int limit);

    /**
     * This method should return a random gif
     *
     * @param search Search query term or phrase.
     * @return random gif
     * @throws GifNotFoundException
     */
    GiphyDTO getRandomGif(String search) throws GifNotFoundException;
}
