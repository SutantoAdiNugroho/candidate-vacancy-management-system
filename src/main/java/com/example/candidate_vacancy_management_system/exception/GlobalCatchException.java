package com.example.candidate_vacancy_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.candidate_vacancy_management_system.dto.BaseErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;

@ControllerAdvice
public class GlobalCatchException {
    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<BaseErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
        BaseErrorResponse errorResponse = new BaseErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setSuccess(false);
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<BaseErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
        BaseErrorResponse errorResponse = new BaseErrorResponse();
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setSuccess(false);
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseErrorResponse> handleErrorRequestInputExceptions(HttpMessageNotReadableException ex,
            WebRequest request) {
        BaseErrorResponse errorResponse = new BaseErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setSuccess(false);

        if (ex.getCause() instanceof InvalidTypeIdException) {
            errorResponse.setMessage("unknown criteria type, must be 'gender', 'age', 'salary' or 'educationLevel'");
        } else {
            errorResponse.setMessage(ex.getMessage());
        }

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseErrorResponse> handleInternalErrorExceptions(Exception ex, WebRequest request) {
        BaseErrorResponse errorResponse = new BaseErrorResponse();
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setSuccess(false);
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
