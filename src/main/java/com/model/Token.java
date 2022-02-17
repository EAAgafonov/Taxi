package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Token {
    private Map<String, String> result;
    private String error;
}
