package com.e1i3.danum.service;

import com.e1i3.danum.repository.ReservationRepository;
import com.e1i3.danum.response.ReadReservationByStoreResponse;
import com.e1i3.danum.response.ReadReservationByStoreResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    public ReadReservationByStoreResponses readReservationByStore(Long storeId){
        List<ReadReservationByStoreResponse> list = reservationRepository.findReservationByStoreId(storeId);
    }
}
