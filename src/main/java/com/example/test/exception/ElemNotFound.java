package com.example.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Исключение - товар не найден  */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElemNotFound extends RuntimeException{
    public ElemNotFound() {

    }

}
