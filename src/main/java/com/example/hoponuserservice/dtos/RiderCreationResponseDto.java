package com.example.hoponuserservice.dtos;

import com.example.hoponuserservice.model.Rider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RiderCreationResponseDto {
    private Rider rider;
    private ResponseStatus responseStatus;
}
