package com.sakerini.exchange.services;

import com.sakerini.exchange.models.GiphyDTO;
import com.sakerini.exchange.exceptions.BaseException;
import com.sakerini.exchange.exceptions.CurrencyException;

public interface GifService {
    GiphyDTO getGif(String currency) throws CurrencyException, BaseException;
}
