package com.desive.gearhead.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException(int id) {
        super("could not find a car with id " + id + ".");
    }

    public CarNotFoundException(long vin) {
        super("could not find a car with vin " + vin + ".");
    }
}
