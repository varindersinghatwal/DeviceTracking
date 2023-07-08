package com.device.tracking.handlers;

import com.device.tracking.accessors.DeviceRecordDAO;
import com.device.tracking.entities.DeviceRecord;
import com.device.tracking.entities.api.DeviceInfoHistoryResponse;
import com.device.tracking.entities.api.DeviceInfoResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GetDeviceInfoHandler {

    private final DeviceRecordDAO deviceRecordDAO;

    @Autowired
    public GetDeviceInfoHandler(DeviceRecordDAO deviceRecordDAO) {
        this.deviceRecordDAO = deviceRecordDAO;
    }

    public DeviceInfoResponse getDeviceInfo(@NonNull final String id) {
        final Optional<DeviceRecord> deviceRecord = deviceRecordDAO.getLatestDeviceRecord(id);
        if (!deviceRecord.isPresent()) {
            return DeviceInfoResponse.builder().errorMessage("Device not found").build();
        }
        return DeviceInfoResponse.builder().deviceInfo(deviceRecord.get().getDeviceInfo()).build();
    }

    public DeviceInfoHistoryResponse getDeviceInfoHistory(@NonNull final String id) {
        final List<DeviceRecord> deviceRecord = deviceRecordDAO.getDeviceInfoHistory(id);
        return DeviceInfoHistoryResponse.builder()
                .deviceInfoList(deviceRecord.stream().map(DeviceRecord::getDeviceInfo).collect(Collectors.toList()))
                .build();
    }
}
