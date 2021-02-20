package com.sakerini.exchange;

import com.sakerini.exchange.models.GiphyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void testControllerOk() {
        String url = "http://localhost:" + port + "/api/get-currency-gif/rub";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<GiphyDTO> response = testRestTemplate.getForEntity(uriBuilder.toUriString(), GiphyDTO.class);
        Assert.isTrue(response.getStatusCode() == HttpStatus.OK, "Should Return 200 Code");
    }

    @Test
    public void testControllerBadRequest() {
        String url = "http://localhost:" + port + "/api/get-currency-gif/rublee";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        ResponseEntity<GiphyDTO> response = testRestTemplate.getForEntity(uriBuilder.toUriString(), GiphyDTO.class);
        Assert.isTrue(response.getStatusCode() == HttpStatus.BAD_REQUEST, "Should Return 400 code");
    }

}
