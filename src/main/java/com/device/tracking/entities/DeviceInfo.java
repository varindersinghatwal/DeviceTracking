package com.device.tracking.entities;

import lombok.*;

@Builder(toBuilder = true)
@Getter
@ToString
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class DeviceInfo {
    @NonNull String id;
    long timestamp;
    String hostName;
    String ipAddress;
    String macAddress;
    OperatingSystem operatingSystem;
}
