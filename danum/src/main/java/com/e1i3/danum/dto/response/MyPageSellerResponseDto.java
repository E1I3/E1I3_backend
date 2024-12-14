package com.e1i3.danum.dto.response;

import com.e1i3.danum.entity.Store;
import com.e1i3.danum.entity.User;
import com.e1i3.danum.enumeration.StoreType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPageSellerResponseDto {

    private Long userId;
    private String userName;
    private Long userBalance;
    private String userEmail;
    private String storeName;
    private StoreType storeType;
    private String storeAddress;
    private Long donateSum;
    private String rewardUrl = "https://danumbucket.s3.ap-northeast-2.amazonaws.com/broze.png";

    @Builder
    public MyPageSellerResponseDto(Store store, User user) {
        this.userId = user.getUserId();
        this.userName = user.getName();
        this.userBalance = user.getBalance();
        this.userEmail = user.getEmail();
        this.storeName = store.getStoreName();
        this.storeType = store.getStoreType();
        this.storeAddress = store.getAddress();
        this.donateSum = user.getDonateSum();
        this.renewUrl();
    }

    public void renewUrl(){
        if(this.donateSum < 10)
            this.rewardUrl =  "https://danumbucket.s3.ap-northeast-2.amazonaws.com/broze.png";
        else if (this.donateSum < 100)
            this.rewardUrl =  "https://danumbucket.s3.ap-northeast-2.amazonaws.com/sliver.png";
        else
            this.rewardUrl =  "https://danumbucket.s3.ap-northeast-2.amazonaws.com/gold.png";
    }

}
