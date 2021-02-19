package com.sakerini.exchange.exceptions;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyException extends BaseException {

    public CurrencyException(String code, String message) {
        super(code, message);
    }
}
