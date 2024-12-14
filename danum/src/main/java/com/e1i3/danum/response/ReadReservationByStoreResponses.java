package com.e1i3.danum.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadReservationByStoreResponses {
    List<ReadReservationByStoreResponse> reservations;

}
