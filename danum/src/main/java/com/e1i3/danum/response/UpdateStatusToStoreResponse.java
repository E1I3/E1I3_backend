package com.e1i3.danum.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStatusToStoreResponse {
    Long userId;
    Long type;
    Long storeId;
}
