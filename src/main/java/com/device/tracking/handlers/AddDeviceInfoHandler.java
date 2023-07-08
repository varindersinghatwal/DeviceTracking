package com.device.tracking.handlers;

import com.device.tracking.accessors.DeviceRecordDAO;
import com.device.tracking.entities.DeviceInfo;
import com.device.tracking.entities.DeviceRecord;
import com.device.tracking.entities.api.AddDeviceInfoRequest;
import com.device.tracking.entities.api.AddDeviceInfoResponse;
import io.micrometer.common.util.StringUtils;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@Log4j2
public class AddDeviceInfoHandler {

    private static String IP_ADDRESS_REGEX = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
    private static String MAC_ADDRESS_REGEX = "^(?:[0-9A-Fa-f]{2}[:-]){5}(?:[0-9A-Fa-f]{2})$";

    private DeviceRecordDAO deviceRecordDAO;

    @Autowired
    public AddDeviceInfoHandler(DeviceRecordDAO deviceRecordDAO) {
        this.deviceRecordDAO = deviceRecordDAO;
    }

    /**
     * Validate the request data {@link AddDeviceInfoRequest} and convert the request data into a device information record
     * and sent the record to DB accessor to persist data in DB.
     *
     * @param request {@link AddDeviceInfoRequest}
     * @return {@link AddDeviceInfoResponse}
     */
    public AddDeviceInfoResponse handleRequest(@NonNull final AddDeviceInfoRequest request) {
        try {
            validateRequest(request);
            final long timestamp = Instant.now().toEpochMilli();
            final DeviceInfo deviceInfo = request.getDeviceInfo();
            final DeviceRecord record = DeviceRecord.builder()
                    .timestamp(timestamp)
                    .deviceId(request.getDeviceInfo().getId())
                    .senderId(request.getSenderId())
                    .senderVersion(request.getSenderVersion())
                    .deviceInfo(deviceInfo.toBuilder().timestamp(timestamp).build())
                    .build();
            deviceRecordDAO.save(record);
        } catch (final RuntimeException ex) {
            return AddDeviceInfoResponse.builder().status("fail").errorMessage(ex.getMessage()).build();
        }
        return AddDeviceInfoResponse.builder().status("success").build();
    }

    private void validateRequest(final AddDeviceInfoRequest request) {
        // validate sender;

        // Validate device info is present
        if(request.getDeviceInfo() == null) {
            throw new IllegalArgumentException("Missing device information");
        }
        // Validate device info is present
        if(StringUtils.isBlank(request.getDeviceInfo().getId())) {
            throw new IllegalArgumentException("Device id cannot be blank");
        }
        // Validate Ip address
        if(StringUtils.isBlank(request.getDeviceInfo().getIpAddress()) || !isValidIPAddress(request.getDeviceInfo().getIpAddress())) {
            throw new IllegalArgumentException("Invalid IP address in device information");
        }
        // Validate MacAddress
        if(StringUtils.isBlank(request.getDeviceInfo().getMacAddress()) || !isValidMacAddress(request.getDeviceInfo().getMacAddress())) {
            throw new IllegalArgumentException("Invalid MAC address in device information");
        }
        // Validate OS information
        if(StringUtils.isBlank(request.getDeviceInfo().getOperatingSystem().getName())) {
            throw new IllegalArgumentException("Missing operating system name");
        }
        if(StringUtils.isBlank(request.getDeviceInfo().getOperatingSystem().getName())) {
            throw new IllegalArgumentException("Missing operating system version");
        }
    }

    private boolean isValidMacAddress(final String macAddress) {
        return macAddress.matches(MAC_ADDRESS_REGEX);
    }

    private boolean isValidIPAddress(final String ipAddress) {
        return ipAddress.matches(IP_ADDRESS_REGEX);
    }
}
