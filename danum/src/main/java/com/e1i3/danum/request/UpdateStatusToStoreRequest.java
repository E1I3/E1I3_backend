package com.e1i3.danum.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStatusToStoreRequest {
    Long userId;
    Long type;
    Long storeId;
}
