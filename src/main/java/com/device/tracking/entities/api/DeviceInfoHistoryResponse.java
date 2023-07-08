package com.device.tracking.entities.api;

import com.device.tracking.entities.DeviceInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfoHistoryResponse {
    List<DeviceInfo> deviceInfoList;
}
