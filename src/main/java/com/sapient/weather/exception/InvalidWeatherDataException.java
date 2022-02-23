
package com.sapient.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidWeatherDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

  
    public InvalidWeatherDataException(String message) {
        super(message);
    }

   
    public InvalidWeatherDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
