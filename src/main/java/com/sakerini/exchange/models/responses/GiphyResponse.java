package com.sakerini.exchange.models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sakerini.exchange.models.GiphyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GiphyResponse {

    @JsonProperty("data")
    private List<GiphyDTO> data;
}
