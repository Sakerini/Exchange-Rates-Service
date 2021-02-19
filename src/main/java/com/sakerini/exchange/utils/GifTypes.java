package com.sakerini.exchange.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GifTypes {
    RICH("rich"),
    POOR("poor"),
    EQUAL("equal");

    private String type;
}
