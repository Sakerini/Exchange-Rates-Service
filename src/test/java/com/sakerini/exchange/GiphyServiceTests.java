package com.sakerini.exchange;

import com.sakerini.exchange.api.GiphyApi;
import com.sakerini.exchange.exceptions.GifNotFoundException;
import com.sakerini.exchange.models.GiphyDTO;
import com.sakerini.exchange.models.responses.GiphyResponse;
import com.sakerini.exchange.services.GiphyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GiphyServiceTests {

    @MockBean(name = "${giphy.service.name}")
    GiphyApi giphyApi;

    @Autowired
    private GiphyService giphyService;

    @Value("${giphy.service.key}")
    private String apiKey;

    @Test
    void testGetGifs() {
        List<GiphyDTO> gifs = new ArrayList<>();
        gifs.add(new GiphyDTO("1", "http:test.com"));
        Mockito.when(giphyApi.getGifs(apiKey, "poor", 1)).thenReturn(new GiphyResponse(gifs));

        List<GiphyDTO> myGifs = giphyService.getGifs("poor", 1);

        assertThat(myGifs.equals(gifs));
    }

    @Test
    void testGetRandomGif() throws GifNotFoundException {
        List<GiphyDTO> gifs = new ArrayList<>();
        gifs.add(new GiphyDTO("1", "http:test.com"));
        gifs.add(new GiphyDTO("2", "http:test.com"));
        gifs.add(new GiphyDTO("3", "http:test.com"));
        Mockito.when(giphyApi.getGifs(apiKey, "poor", 1)).thenReturn(new GiphyResponse(gifs));

        GiphyDTO myGif = giphyService.getRandomGif("poor");
        assertThat(myGif.getId().equals("1") ||
                myGif.getId().equals("2") ||
                myGif.getId().equals("3"));
    }
}
