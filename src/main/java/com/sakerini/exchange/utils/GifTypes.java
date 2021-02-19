package com.sakerini.exchange.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * An enum for searching phrases for the gifs
 */
@Getter
@AllArgsConstructor
public enum GifTypes {
    RICH("rich"),
    POOR("poor"),
    EQUAL("equal");

    private String type;
}
