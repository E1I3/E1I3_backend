package com.e1i3.danum.dto.response;

import com.e1i3.danum.dto.request.CreateUserRequest;
import com.e1i3.danum.entity.User;
import lombok.*;
import org.springframework.format.datetime.standard.Jsr310DateTimeFormatAnnotationFormatterFactory;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    Long userId;
    Long type;
    Long storeId;

}
