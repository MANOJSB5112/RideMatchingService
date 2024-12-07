package com.example.hoponuserservice.dtos;

import com.example.hoponuserservice.model.Rider;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RiderListDto {
    private List<Rider> riders;
    private ResponseStatus responseStatus;
}
