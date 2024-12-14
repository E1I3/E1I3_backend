package com.e1i3.danum.service;

import com.e1i3.danum.entity.Reservation;
import com.e1i3.danum.enumeration.ResvType;
import com.e1i3.danum.repository.ReservationRepository;
import com.e1i3.danum.request.UpdateReservationByReserveRequest;
import com.e1i3.danum.response.ReadReservationByStoreResponse;
import com.e1i3.danum.response.ReadReservationByStoreResponses;
import com.e1i3.danum.response.UpdateReservationByReserveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public ReadReservationByStoreResponses readReservationByStore(Long storeId){
        List<ReadReservationByStoreResponse> list = reservationRepository.findReservationByStoreId(storeId);

        return ReadReservationByStoreResponses.builder().reservations(list).build();
    }
    public UpdateReservationByReserveResponse updateReservationByReserveId(UpdateReservationByReserveRequest updateReservationByReserveRequest){
        Reservation reservation = reservationRepository.findById(updateReservationByReserveRequest.getReserveId()).orElseThrow(NoSuchElementException::new);
        reservation.setResvType(ResvType.COMPLETE);
        reservationRepository.save(reservation);

        return UpdateReservationByReserveResponse.builder()
                .reserveId(reservation.getResvId())
                .resvTime(reservation.getResvTime())
                .resvType(reservation.getResvType())
                .productId(reservation.getProduct().getProductId())
                .productName(reservation.getProduct().getProductName())
                .productUrl(reservation.getProduct().getProductUrl())
                .price(reservation.getProduct().getPrice())
                .count(reservation.getProduct().getCount())
                .build();
    }
}
