package com.e1i3.danum.response;

import com.e1i3.danum.dto.response.ProductResponseDto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadStoreAndProductResponse {
    Long storeId;
    String storeUrl;
    String storeAddress;
    String storeName;
    List<ReadProductResponse> products;
}
