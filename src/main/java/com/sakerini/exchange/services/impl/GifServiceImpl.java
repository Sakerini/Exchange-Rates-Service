package com.sakerini.exchange.services.impl;

import com.sakerini.exchange.models.GiphyDTO;
import com.sakerini.exchange.services.GiphyService;
import com.sakerini.exchange.services.OpenExchangeService;
import com.sakerini.exchange.exceptions.BaseException;
import com.sakerini.exchange.exceptions.CurrencyException;
import com.sakerini.exchange.services.GifService;
import com.sakerini.exchange.utils.GifTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class GifServiceImpl implements GifService {

    private final OpenExchangeService openExchangeService;
    private final GiphyService giphyService;

    @Autowired
    public GifServiceImpl(OpenExchangeService openExchangeService, GiphyService giphyService) {
        this.openExchangeService = openExchangeService;
        this.giphyService = giphyService;
    }

    @Override
    public GiphyDTO getGif(String currency) throws BaseException {
        log.info("Inside GifServiceImpl getGif method");

        BigDecimal difference = openExchangeService.getChangeRatio(currency.toUpperCase());
        if (difference == null) {
            log.error("getGiff method: Could not get new currency ratio");
            throw new CurrencyException("code-2", "Could not get new currency ratio");
        }

        GiphyDTO giphyDto;
        if (difference.compareTo(BigDecimal.ZERO) > 0) {
            giphyDto = giphyService.getRandomGif(GifTypes.RICH.getType());
        } else if (difference.compareTo(BigDecimal.ZERO) < 0) {
            giphyDto = giphyService.getRandomGif(GifTypes.POOR.getType());
        } else {
            giphyDto = giphyService.getRandomGif(GifTypes.EQUAL.getType());
        }

        return giphyDto;
    }
}
