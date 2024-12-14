package com.e1i3.danum.controller;

import com.e1i3.danum.request.UpdateReservationByReserveRequest;
import com.e1i3.danum.request.UpdateTradeInfoRequest;
import com.e1i3.danum.response.ReadReservationByStoreResponses;
import com.e1i3.danum.response.UpdateReservationByReserveResponse;
import com.e1i3.danum.response.ReadReservationByStoreResponse;
import com.e1i3.danum.response.ReadStoreResponses;
import com.e1i3.danum.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "예약 API", description = "예약 관련 기능 API입니다.")
@RequestMapping("/")
public class ReservationController {
    private final ReservationService reservationService;
    @Operation(summary = "가게 id를 기반으로 사장님 예약 정보 확인", description = "가게 id를 기반으로 예약 정보 확인")
    @GetMapping("/account/reservation/store")
    public ResponseEntity<ReadReservationByStoreResponses> readReservationByStore(@RequestParam Long storeId){
        ReadReservationByStoreResponses result = reservationService.readReservationByStore(storeId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PutMapping("/mypage/sellers/reservation")
    @Operation(summary = "사장님 예약 확정", description = "사장님 예약 확정")
    public ResponseEntity<UpdateReservationByReserveResponse> updateReservationByReserveId(@RequestBody UpdateReservationByReserveRequest updateReservationByReserveRequest){
        UpdateReservationByReserveResponse result = reservationService.updateReservationByReserveId(updateReservationByReserveRequest);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "유저 id를 기반으로 사용자 예약 정보 확인", description = "유저 id를 기반으로 사용자 예약 정보 확인")
    @GetMapping("/account/reservation/user")
    public ResponseEntity<ReadReservationByStoreResponses> readReservationByUser(@RequestParam Long userId){
        ReadReservationByStoreResponses result = reservationService.readReservationByUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @Operation(summary = "거래 진행 시 상태 변경", description = "거래 시작을 누를 경우 진행됩니다.")
    @PutMapping("/home/store/trade")
    public ResponseEntity<Void> updateTradeInfo(@RequestBody UpdateTradeInfoRequest updateTradeInfoRequest){
        reservationService.updateTradeInfo(updateTradeInfoRequest);

        return ResponseEntity.ok().build();
    }
    @Operation(summary = "나눔 진행 시 상태 변경", description = "나눔 시작을 누를 경우 진행됩니다.")
    @PutMapping("/home/store/share")
    public ResponseEntity<Void> updateShareInfo(@RequestBody UpdateTradeInfoRequest updateTradeInfoRequest){
        reservationService.updateShareInfo(updateTradeInfoRequest);

        return ResponseEntity.ok().build();
    }
}
