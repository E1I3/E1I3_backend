package com.e1i3.danum.response;

import com.e1i3.danum.enumeration.ResvType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateReservationByReserveResponse {
    Long reserveId;
    LocalDateTime resvTime;
    ResvType resvType;
    Long productId;
    String productName;
    String productUrl;
    Long price;
    Long count;
}
