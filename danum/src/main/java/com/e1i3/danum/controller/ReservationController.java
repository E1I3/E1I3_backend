package com.e1i3.danum.controller;

import com.e1i3.danum.response.ReadReservationByStoreResponse;
import com.e1i3.danum.response.ReadReservationByStoreResponses;
import com.e1i3.danum.response.ReadStoreResponses;
import com.e1i3.danum.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/account/reservation")
    public ResponseEntity<ReadReservationByStoreResponses> readReservationByStore(@RequestParam Long storeId){
        ReadReservationByStoreResponses result = reservationService.readReservationByStore(storeId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
