package com.sakerini.exchange.exceptions;

import com.sakerini.exchange.models.responses.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CurrencyException.class)
    protected ResponseEntity<ErrorResponse> handleConc(CurrencyException ex, WebRequest request) {
        log.error("Error CurrencyException");
        return new ResponseEntity<>(new ErrorResponse(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GifNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleConc(GifNotFoundException ex, WebRequest request) {
        log.error("Exception GifNotFound");
        return new ResponseEntity<>(new ErrorResponse(ex.getCode(), ex.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        log.error("Internal Server Error");
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ErrorResponse("code-1", "internal exception occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
