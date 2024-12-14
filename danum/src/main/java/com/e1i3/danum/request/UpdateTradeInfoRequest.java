package com.e1i3.danum.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateTradeInfoRequest {
    Long userId;
    Long productId;
    Long count;
}
