package com.e1i3.danum.controller;

import com.e1i3.danum.request.UpdateReservationByReserveRequest;
import com.e1i3.danum.response.ReadReservationByStoreResponses;
import com.e1i3.danum.response.UpdateReservationByReserveResponse;
import com.e1i3.danum.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/mypage/sellers/reservation")
    public ResponseEntity<UpdateReservationByReserveResponse> updateReservationByReserveId(@RequestBody UpdateReservationByReserveRequest updateReservationByReserveRequest){
        UpdateReservationByReserveResponse result = reservationService.updateReservationByReserveId(updateReservationByReserveRequest);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
