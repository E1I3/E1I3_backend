package com.e1i3.danum.dto.request;


import com.e1i3.danum.entity.Store;
import com.e1i3.danum.entity.User;
import com.e1i3.danum.enumeration.StoreStatus;
import com.e1i3.danum.enumeration.StoreType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class StoreRegisterRequestDto {

    private Long userId;
    private String storeName;
    private String address;
    private String storeUrl;
    private StoreType storeType;

    private Boolean type;

    public Store toEntity(User user) {
        return Store.builder()
                .address(address)
                .storeStatus(StoreStatus.NONE)
                .storeName(storeName)
                .storeUrl(storeUrl)
                .user(user)
                .build();
    }


}
