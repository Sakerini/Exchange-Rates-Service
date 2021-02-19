package com.sakerini.exchange.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GifNotFoundException extends BaseException {
    public GifNotFoundException(String code, String message) {
        super(code, message);
    }
}
