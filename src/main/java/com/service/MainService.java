package com.service;

import com.model.trip.Trip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainService {
    private final IWayService iWayService;


    //find price for a ride
    public List<ResponseEntity<Object>> getPrices(String start, String finish, String currency, String lang) {
        Executor executor = Executors.newFixedThreadPool(10);
        List<ResponseEntity<Object>> results = new ArrayList<>();

        CompletableFuture<ResponseEntity<Object>> completableFuture1 = CompletableFuture.supplyAsync(() -> iWayService.getPrices(start, finish, currency, lang), executor);

        try {
            ResponseEntity<Object> res = completableFuture1.get();
            results.add(res);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return results;
    }

    public ResponseEntity<Object> orderRide(ArrayList<Trip> trips) {
        //here some logic to decide which vendor to use
        return iWayService.orderRide(trips);
    }

    public ResponseEntity<Object> cancelRide() {
        return iWayService.cancelRide();
    }


}
