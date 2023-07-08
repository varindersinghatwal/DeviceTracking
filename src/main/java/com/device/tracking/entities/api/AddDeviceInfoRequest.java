package com.device.tracking.entities.api;

import com.device.tracking.entities.DeviceInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddDeviceInfoRequest {
    String senderId;
    String senderVersion;
    DeviceInfo deviceInfo;
}
