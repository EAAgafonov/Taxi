package com.service;

import com.feign.IWayClient;
import com.model.AuthRequest;
import com.model.CancelRequest;
import com.model.OrderRequest;
import com.model.Token;
import com.model.trip.Trip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class IWayService {
    private final IWayClient iWayClient;

    @Value("${iway.user.id}")
    String user_id;
    @Value("${iway.user.login}")
    String user_login;
    @Value("${iway.user.password}")
    String password;

    public ResponseEntity<Object> orderRide(ArrayList<Trip> trips) {
        String token = authLogin();
        OrderRequest orderRequest = new OrderRequest(user_id, trips);
        return iWayClient.orderRide(token, orderRequest);
    }

    public ResponseEntity<Object> cancelRide() {
        String token = authLogin();
        CancelRequest cancelRequest = new CancelRequest("1995461", "113531548");
        return iWayClient.cancelRide(token, cancelRequest);
    }

    public ResponseEntity<Object> getPrices(String start, String finish, String currency, String lang) {
        String token = authLogin();
        return iWayClient.getPrices(token, user_id, start, finish, currency, lang);
    }

    private String authLogin() {
        AuthRequest data = new AuthRequest(user_login, user_id, password);
        ResponseEntity<Token> entity = iWayClient.authLogin(data);

        if ((Objects.requireNonNull(entity.getBody()).getError()) != null) {
            log.error("Auth went wrong - " + entity.getBody().getError());
            return entity.getBody().getError();
        }

        return "Bearer " + entity.getBody().getResult().get("token");

    }
}
