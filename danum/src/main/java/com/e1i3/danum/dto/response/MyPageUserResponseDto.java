package com.e1i3.danum.dto.response;

import com.e1i3.danum.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MyPageUserResponseDto {

    private Long id;
    private String name;
    private Long balance;
    private String email;

    @Builder
    public MyPageUserResponseDto(User user) {
        this.id = user.getUserId();
        this.name = user.getName();
        this.balance = user.getBalance();
        this.email = user.getEmail();
    }

}
