package com.example.hoponuserservice.GlobalException;

import com.example.hoponuserservice.Exceptions.RiderExceptions;
import com.example.hoponuserservice.dtos.ExceptionDto;
import com.example.hoponuserservice.dtos.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandlerClass {

    @ExceptionHandler(RiderExceptions.class)
    public ResponseEntity<ExceptionDto> handleRiderCreationException(Exception exception)
    {
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        exceptionDto.setResponseStatus(ResponseStatus.FAILURE);
        return new ResponseEntity<>(exceptionDto, HttpStatus.FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleGlobalException(Exception exception)
    {
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setMessage(exception.getMessage());
        exceptionDto.setResponseStatus(ResponseStatus.FAILURE);
        return new ResponseEntity<>(exceptionDto, HttpStatus.FOUND);
    }

}
