package com.sondeos.codechallenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Global controller advisor
 *
 * @author Jose Torrealba
 *
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    /**
     * Method for handle exception
     * @param ex exception
     * @param request object send
     * @return response
     * @since 1.0
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleObjectNotFoundException(
            ObjectNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Object not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    /**
     * Method for handle exception
     * @param ex exception
     * @param request object send
     * @return response
     * @since 1.0
     */
    @ExceptionHandler(DuplicatedObjectDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleDuplicatedObjectDataException(
            DuplicatedObjectDataException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Object has already been persisted.");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

}
