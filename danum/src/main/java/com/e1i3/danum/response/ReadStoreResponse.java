package com.e1i3.danum.response;

import com.e1i3.danum.enumeration.StoreStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadStoreResponse {
    Long storeId;
    Float latitude;
    Float longitude;
    String storeName;
    String address;
    String storeUrl;
    StoreStatus storeStatus;
}
