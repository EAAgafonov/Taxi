package com.model;


import com.model.trip.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequest {
    private String user_id;
    List<Trip> trips;
}
