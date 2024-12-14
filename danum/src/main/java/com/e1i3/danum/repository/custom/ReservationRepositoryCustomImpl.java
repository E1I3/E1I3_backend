package com.e1i3.danum.repository.custom;

import com.e1i3.danum.response.ReadReservationByStoreResponse;

import java.util.List;

public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom{
    List<ReadReservationByStoreResponse> findReservationByStoreId(Long storeId){

    }
}

{
        "reservations": [
        {
        "reserveId": Long,
        "resv_time": TimeStamp,
        "resv_type": Enum,
        "productId": String,
        "productName" : String,
        "price" : Long,
        "count" : Integer
        }
        ]
        }