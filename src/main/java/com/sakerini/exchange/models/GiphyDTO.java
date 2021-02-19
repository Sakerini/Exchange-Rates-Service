package com.sakerini.exchange.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiphyDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String url;
}
