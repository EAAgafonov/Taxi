package com.model.trip;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Trip {
    private String price_id;
    private Location start_location;
    private Location finish_location;
    private List<Passenger> passengers;

}
