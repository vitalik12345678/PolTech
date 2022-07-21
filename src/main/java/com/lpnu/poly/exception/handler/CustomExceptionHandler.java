package com.lpnu.poly.exception.handler;

import com.lpnu.poly.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotExistsException.class)
    public final ResponseEntity<Object> handleNotExistException(NotExistsException e) {
        return buildExceptionBody(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<Object> handleForbiddenException(ForbiddenException e){
        return buildExceptionBody(e, HttpStatus.FORBIDDEN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
        return buildExceptionBody(e, BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ExistsException.class)
    public final ResponseEntity<Object> handleExistsException(ExistsException e) {
        return buildExceptionBody(e, HttpStatus.CONFLICT);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(IncorrectInputException.class)
    public final ResponseEntity<Object> handleIncorrectInputException(IncorrectInputException exception) {
        return buildExceptionBody(exception, BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        return buildExceptionBody(exception, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return buildExceptionBody(new BadRequestException(exception.getMessage()), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> mapMessage = new HashMap<>();
        mapMessage.put("startDate Invalid future date", "Дата не може бути у минулому");

        StringBuilder sb = new StringBuilder();
        exception.getBindingResult().getFieldErrors().forEach((error) -> {
            if ( (error.getField() + " " + error.getDefaultMessage())
                    .contains("startDate Invalid future date") ) {
                sb.append(mapMessage.get("startDate Invalid future date")).append(" and ");
            } else {
                sb.append(error.getField()).append(" ").append(error.getDefaultMessage()).append(" and ");
            }
        });
        sb.setLength(sb.length() - 5);
        return buildExceptionBody(new BadRequestException(sb.toString()), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return buildExceptionBody(new MethodNotSupportedException(exception.getMessage()), status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        return buildExceptionBody(new BadRequestException(exception.getMessage()), status);
    }

    private ResponseEntity<Object> buildExceptionBody(Exception exception, HttpStatus httpStatus) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .MESSAGE(exception.getMessage())
                .STATUS(httpStatus)
                .HTTP_CODE(httpStatus.value())
                .build();
        return ResponseEntity
                .status(httpStatus)
                .body(exceptionDetails);
    }


}
