package com.example.hoponuserservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto {
    private String message;
    private ResponseStatus responseStatus;
}
