package com.joe.restapi.error;

import com.joe.restapi.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
        public ResponseEntity<ErrorMessage> message(
            DepartmentNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);
    }

    @ExceptionHandler(DepartmentPropertiesException.class)
    public ResponseEntity<ErrorMessage> message1(
            DepartmentPropertiesException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

    @ExceptionHandler(DepartmentExistsException.class)
    public ResponseEntity<ErrorMessage> message2(
            DepartmentExistsException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(
                exception.getMessage(), HttpStatus.CONFLICT);

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorMessage);
    }
}
