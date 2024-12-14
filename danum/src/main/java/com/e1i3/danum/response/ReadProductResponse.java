package com.e1i3.danum.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadProductResponse {
    Long productId;
    String productName;
    Long productPrice;
    Long productCount;
    String productUrl;
}
