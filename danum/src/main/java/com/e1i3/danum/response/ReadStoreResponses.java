package com.e1i3.danum.response;

import com.e1i3.danum.entity.Store;
import com.e1i3.danum.enumeration.StoreStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadStoreResponses {
    List<ReadStoreResponse> stores;

    static public ReadStoreResponses toDto(List<Store> stores){
        List<ReadStoreResponse> readStoreResponseList = new ArrayList<>();

        for (Store store: stores){
            ReadStoreResponse readStoreResponse = ReadStoreResponse.builder()
                    .storeId(store.getStoreId())
                    .latitude(store.getLatitude())
                    .longitude(store.getLongitude())
                    .storeName(store.getStoreName())
            .address(store.getAddress())
            .storeUrl(store.getStoreUrl())
            .storeStatus(store.getStoreStatus())
                    .build();

            readStoreResponseList.add(readStoreResponse);
        }

        return ReadStoreResponses.builder().stores(readStoreResponseList).build();
    }
}
