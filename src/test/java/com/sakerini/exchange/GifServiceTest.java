package com.sakerini.exchange;

import com.sakerini.exchange.exceptions.BaseException;
import com.sakerini.exchange.models.GiphyDTO;
import com.sakerini.exchange.services.GifService;
import com.sakerini.exchange.services.GiphyService;
import com.sakerini.exchange.services.OpenExchangeService;
import com.sakerini.exchange.utils.GifTypes;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@SpringBootTest
public class GifServiceTest {

    @MockBean
    OpenExchangeService openExchangeService;
    @MockBean
    GiphyService giphyService;

    @Autowired
    private GifService gifService;

    @Test
    void getGifTest() throws BaseException {
        Mockito.when(openExchangeService.getChangeRatio("RUB")).thenReturn(new BigDecimal(0.0));
        Mockito.when(openExchangeService.getChangeRatio("BGN")).thenReturn(new BigDecimal(+10.4312));
        Mockito.when(openExchangeService.getChangeRatio("GBN")).thenReturn(new BigDecimal(-10.4312));
        Mockito.when(giphyService.getRandomGif(GifTypes.RICH.getType())).thenReturn(new GiphyDTO("1", "test"));
        Mockito.when(giphyService.getRandomGif(GifTypes.POOR.getType())).thenReturn(new GiphyDTO("2", "test"));
        Mockito.when(giphyService.getRandomGif(GifTypes.EQUAL.getType())).thenReturn(new GiphyDTO("3", "test"));

        GiphyDTO gif = gifService.getGif("RUB");
        Assert.isTrue(gif.getId().equals("3"), "Rub is equal so should return id 3 gif");
        gif = gifService.getGif("BGN");
        Assert.isTrue(gif.getId().equals("1"), "BGN is rich should return id 1 gif");
        gif = gifService.getGif("GBN");
        Assert.isTrue(gif.getId().equals("2"), "GBN is poor should return id 2 gif");
    }

}
