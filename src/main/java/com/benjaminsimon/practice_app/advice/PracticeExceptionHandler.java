package com.benjaminsimon.practice_app.advice;

import com.benjaminsimon.practice_app.dto.ExceptionDto;
import com.benjaminsimon.practice_app.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PracticeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleException(PostNotFoundException ex) {
        ExceptionDto exceptionDto = new ExceptionDto();

        exceptionDto.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleException(Exception ex) {
        ExceptionDto exceptionDto = new ExceptionDto();

        exceptionDto.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionDto.setMessage(ex.getMessage());
        exceptionDto.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
