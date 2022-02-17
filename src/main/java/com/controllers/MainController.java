package com.controllers;

import com.model.trip.Location;
import com.model.trip.Passenger;
import com.model.trip.Trip;
import com.service.MainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "TestController")
public class MainController {
    private final MainService mainService;

    @Operation(summary = "Get prices for a ride")
    @GetMapping("/get")
    public List<ResponseEntity<Object>> getPrices(
            @RequestParam(name = "start", defaultValue = "55.780300,37.534077") @Parameter(description = "Координаты") String start,
            @RequestParam(name = "finish", defaultValue = "55.759949,37.578277") @Parameter(description = "Координаты") String finish,
            @RequestParam(name = "currency", defaultValue = "RUB") @Parameter(description = "EUR, USD, RUB, CNY, GBP") String currency

    ) {
        String lang = "ru";
        return mainService.getPrices(start, finish, currency, lang);
    }

    @Operation(summary = "Order a ride")
    @PostMapping("/order")
    public ResponseEntity<Object> orderRide(
            @RequestParam(name = "name", defaultValue = "name") @Parameter(description = "") String name,
            @RequestParam(name = "phone", defaultValue = "+7(926)1234567") @Parameter(description = "") String phone,
            @RequestParam(name = "email", defaultValue = "email@qwe.ru") @Parameter(description = "") String email,
            @RequestParam(name = "start", defaultValue = "Полины Осипенко 12к1") @Parameter(description = "start") String start,
            @RequestParam(name = "finish", defaultValue = "Конюшковская ул., 31 строение 1") @Parameter(description = "finish") String finish
    ) {
        ArrayList<Trip> trips = new ArrayList<>();
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger(name, phone, email));

        Trip trip = new Trip(
                "3033804",
                new Location(start, "55.78030074120705,37.534077402753645", "2022-02-17 23:00"),
                new Location(finish, "55.759949590385915,37.57827756981078", null),
                passengers);
        trips.add(trip);

        return mainService.orderRide(trips);
    }

    @Operation(summary = "Cancel ride")
    @PostMapping("/cancel")
    public ResponseEntity<Object> cancelRide() {
        return mainService.cancelRide();
    }





}
