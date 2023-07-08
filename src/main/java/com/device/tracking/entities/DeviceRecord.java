package com.device.tracking.entities;

import lombok.*;

@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRecord {
    String deviceId;
    long timestamp;
    String senderId;
    String senderVersion;
    DeviceInfo deviceInfo;
}
