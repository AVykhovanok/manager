package com.vykhovanok.manager.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlreadyExistException extends IllegalStateException {

    private static final String ALREADY_EXIST_EXCEPTION = "Already exist";

    public AlreadyExistException(String message) {
        super(message.isEmpty() ? ALREADY_EXIST_EXCEPTION : message);
    }

}
