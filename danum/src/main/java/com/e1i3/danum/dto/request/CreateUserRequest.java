package com.e1i3.danum.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateUserRequest {
    String name;
    String email;
}
