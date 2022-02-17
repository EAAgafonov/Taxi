package com.feign;

import com.model.AuthRequest;
import com.model.CancelRequest;
import com.model.OrderRequest;
import com.model.Token;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IWay {

    @PostMapping(value = "/v1/auth/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Token> authLogin(
            @RequestBody AuthRequest requestBody
    );

    @GetMapping("/v3/prices")
    ResponseEntity<Object> getPrices(
            @RequestHeader("Authorization") String token,
            @RequestParam("user_id") String user_id,
            @RequestParam("start_place_point") String start_place_point,
            @RequestParam("finish_place_point") String finish_place_point,
            @RequestParam("currency") String currency,
            @RequestParam("lang") String lang
    );

    @PostMapping(value = "/v3/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> orderRide(
            @RequestHeader("Authorization") String token,
            @RequestBody OrderRequest orderRequest
            );

    @PostMapping(value = "/v1/orders/trips/cancel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> cancelRide(
            @RequestHeader("Authorization") String token,
            @RequestBody CancelRequest cancelRequest

    );
}
