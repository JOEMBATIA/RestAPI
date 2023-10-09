package com.joe.restapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Setter
@Getter
public class ErrorMessage {

    private final String message;
    private final HttpStatus status;

}
