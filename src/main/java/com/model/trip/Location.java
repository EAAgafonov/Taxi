package com.model.trip;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private String address;
    private String location;
    private String time;
}
