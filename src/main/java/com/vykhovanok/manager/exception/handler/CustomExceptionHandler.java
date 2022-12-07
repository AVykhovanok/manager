package com.vykhovanok.manager.exception.handler;

import com.vykhovanok.manager.dto.exception.ExceptionMapResponse;
import com.vykhovanok.manager.dto.exception.ExceptionResponse;
import com.vykhovanok.manager.exception.AlreadyExistException;
import com.vykhovanok.manager.exception.NotExistException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotExistException.class)
    public final ResponseEntity<Object> handleNotExistException(NotExistException exception) {

        return buildExceptionBody(exception, NOT_FOUND);

    }

    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<Object> handleAlreadyExistException(AlreadyExistException exception) {

        return buildExceptionBody(exception, FORBIDDEN);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleAlreadyExistException(DataIntegrityViolationException exception) {

        return buildExceptionBody(new Exception(exception.getMostSpecificCause().getMessage()), CONFLICT);

    }

    private ResponseEntity<Object> buildExceptionBody(Exception exception, HttpStatusCode httpStatusCode) {

        ExceptionResponse exceptionResponse = ExceptionResponse
                .builder()
                .status(httpStatusCode)
                .message(exception.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();

        log.debug(exception.getMessage());

        return ResponseEntity
                .status(httpStatusCode)
                .body(exceptionResponse);

    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {

        Map<String,String> map = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach( exc -> map.put(exc.getField(), exc.getDefaultMessage()));

        ExceptionMapResponse exceptionMapResponse = ExceptionMapResponse
                .builder()
                .status(status)
                .messageMap(map)
                .timeStamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(status).body(exceptionMapResponse);

    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatusCode status, WebRequest request) {

        return buildExceptionBody(ex, status);

    }

}
