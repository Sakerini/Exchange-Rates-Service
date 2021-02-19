package com.sakerini.exchange.controller;

import com.sakerini.exchange.exceptions.BaseException;
import com.sakerini.exchange.models.GiphyDTO;
import com.sakerini.exchange.services.GifService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ExchangeController {

    private final GifService gifService;

    @Autowired
    public ExchangeController(GifService gifService) {
        this.gifService = gifService;
    }

    /**
     * This Controller's method calls external services and returns a gifs depending on currency rate change
     *
     * @param currency the currency to be checked
     * @return gif
     * @throws BaseException External Server Errors
     */
    @GetMapping("/get-currency-gif/{currency}")
    public ResponseEntity<GiphyDTO> getCurrencyGif(@PathVariable(value = "currency") String currency) throws BaseException {
        log.info("Inside getCurrencyGif controller's method body");
        return new ResponseEntity<>(gifService.getGif(currency), HttpStatus.OK);
    }
}

