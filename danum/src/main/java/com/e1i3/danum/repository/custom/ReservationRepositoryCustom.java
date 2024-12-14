package com.e1i3.danum.repository.custom;

import com.e1i3.danum.response.ReadReservationByStoreResponse;

import java.util.List;

public interface ReservationRepositoryCustom {
    List<ReadReservationByStoreResponse> findReservationByStoreId(Long storeId);
    List<ReadReservationByStoreResponse> findReservationByUserId(Long userId);
}
