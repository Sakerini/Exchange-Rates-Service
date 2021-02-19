package com.sakerini.exchange.services;

import com.sakerini.exchange.exceptions.BaseException;
import com.sakerini.exchange.exceptions.CurrencyException;
import com.sakerini.exchange.models.GiphyDTO;

public interface GifService {

    /**
     * This method should return gif depending on currency rate changes
     *
     * @param currency the currency to be checked
     * @return gif
     * @throws CurrencyException
     * @throws BaseException
     */
    GiphyDTO getGif(String currency) throws CurrencyException, BaseException;
}
