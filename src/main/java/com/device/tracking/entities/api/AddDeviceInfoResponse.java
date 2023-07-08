package com.device.tracking.entities.api;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddDeviceInfoResponse {
    String status;
    String errorMessage;
}
