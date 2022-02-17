package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CancelRequest {
    private String order_id;
    private String transaction;
}
