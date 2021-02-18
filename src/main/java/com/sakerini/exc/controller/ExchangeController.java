package com.sakerini.exc.controller;

import com.sakerini.exc.datasource.entity.GiphyDto;
import com.sakerini.exc.datasource.service.GiphyService;
import com.sakerini.exc.datasource.service.OxcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/api")
@EnableScheduling
public class ExchangeController {

    private final String ON_INCREASED = "rich";
    private final String ON_DECREASED = "poor";
    private final String ON_EQUAL = "equal";

    @Autowired
    private OxcService oxcService;
    @Autowired
    private GiphyService giphyService;

    @GetMapping("/get-currency-gif/{currency}")
    public ResponseEntity<GiphyDto> getCurrencyGif(@PathVariable String currency) {
        log.info("Inside getCurrencyGif controller's method body");

        BigDecimal difference = oxcService.getChangeRatio(currency.toUpperCase());
        if (difference == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else if (difference.compareTo(BigDecimal.ZERO) > 0) {
            GiphyDto giphyDto = giphyService.getRandomGiff(ON_INCREASED);
            if (giphyDto == null) {
                return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
            }
            return new ResponseEntity<>(giphyDto, HttpStatus.OK);
        } else if (difference.compareTo(BigDecimal.ZERO) < 0) {
            GiphyDto giphyDto = giphyService.getRandomGiff(ON_DECREASED);
            if (giphyDto == null) {
                return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
            }
            return new ResponseEntity<>(giphyDto, HttpStatus.OK);
        }

        GiphyDto giphyDto = giphyService.getRandomGiff(ON_EQUAL);
        if (giphyDto == null) {
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(giphyDto, HttpStatus.OK);
    }
}

