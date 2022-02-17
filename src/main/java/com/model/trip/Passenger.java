package com.model.trip;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Passenger {
    private String name;
    private String phone;
    private String email;
}
