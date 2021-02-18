package com.sakerini.exc.datasource.service;

import com.sakerini.exc.datasource.entity.GiphyDto;

import java.util.List;

public interface GiphyService {
    List<GiphyDto> getGifs(String search, int limit);
    GiphyDto getRandomGiff(String search);
}
