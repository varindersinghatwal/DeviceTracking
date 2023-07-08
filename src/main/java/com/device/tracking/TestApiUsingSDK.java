package com.device.tracking;

import com.device.tracking.client.DeviceTrackingClientSDK;
import com.device.tracking.entities.DeviceInfo;
import com.device.tracking.entities.OperatingSystem;
import com.device.tracking.entities.api.AddDeviceInfoRequest;
import com.device.tracking.entities.api.AddDeviceInfoResponse;
import com.device.tracking.entities.api.DeviceInfoHistoryResponse;
import com.device.tracking.entities.api.DeviceInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestApiUsingSDK {

    public static void main(String[] args) {
        /**
         * Make sure service is up and running before running this test code
         * To start the service run this cmd:: mvn clean spring-boot:run
         */

        final ObjectMapper objectMapper = new ObjectMapper();
        final DeviceTrackingClientSDK sdk = new DeviceTrackingClientSDK();
        try {
            // Call add device info API
            AddDeviceInfoResponse response1 = sdk.callAddDeviceInfoRequest(getRequest1());
            System.out.println(objectMapper.writeValueAsString(response1));
            AddDeviceInfoResponse response2 = sdk.callAddDeviceInfoRequest(getRequest2());
            System.out.println(objectMapper.writeValueAsString(response2));
            AddDeviceInfoResponse response3 = sdk.callAddDeviceInfoRequest(getRequest3());
            System.out.println(objectMapper.writeValueAsString(response3));
            AddDeviceInfoResponse response4 = sdk.callAddDeviceInfoRequest(getRequest4());
            System.out.println(objectMapper.writeValueAsString(response4));

            // Call GetDeviceInfo api
            DeviceInfoResponse deviceInfoResponse = sdk.callGetDeviceInfo("centos");
            System.out.println(objectMapper.writeValueAsString(deviceInfoResponse));

            // Call GetDeviceInfoHistory api
            DeviceInfoHistoryResponse deviceInfoResponseHistory = sdk.callGetDeviceInfoHistory("ventura");
            System.out.println(objectMapper.writeValueAsString(deviceInfoResponseHistory));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static AddDeviceInfoRequest getRequest1() {
        final AddDeviceInfoRequest request = AddDeviceInfoRequest.builder()
                .senderId("test")
                .senderVersion("1.0")
                .deviceInfo(DeviceInfo.builder()
                        .id("ventura")
                        .hostName("mymac@company.com")
                        .ipAddress("127.0.0.1")
                        .macAddress("02:01:5e:00:53:aa")
                        .operatingSystem(OperatingSystem.builder()
                                .name("Ventura")
                                .version("13.3")
                                .build()
                        ).build()
                ).build();
        return request;
    }

    private static AddDeviceInfoRequest getRequest2() {
        final AddDeviceInfoRequest request = AddDeviceInfoRequest.builder()
                .senderId("test")
                .senderVersion("1.0")
                .deviceInfo(DeviceInfo.builder()
                        .id("centos")
                        .hostName("mymac@company.com")
                        .ipAddress("127.0.0.2")
                        .macAddress("00:00:4c:00:44:bb")
                        .operatingSystem(OperatingSystem.builder()
                                .name("CentOS")
                                .version("5.3")
                                .build()
                        ).build()
                ).build();
        return request;
    }

    private static AddDeviceInfoRequest getRequest3() {
        final AddDeviceInfoRequest request = AddDeviceInfoRequest.builder()
                .senderId("test")
                .senderVersion("1.0")
                .deviceInfo(DeviceInfo.builder()
                        .id("rhel")
                        .hostName("mymac@company.com")
                        .ipAddress("127.0.0.3")
                        .macAddress("00:00:3a:00:33:cc")
                        .operatingSystem(OperatingSystem.builder()
                                .name("RHEL")
                                .version("6.1")
                                .build()
                        ).build()
                ).build();
        return request;
    }

    private static AddDeviceInfoRequest getRequest4() {
        final AddDeviceInfoRequest request = AddDeviceInfoRequest.builder()
                .senderId("test")
                .senderVersion("1.0")
                .deviceInfo(DeviceInfo.builder()
                        .id("ventura")
                        .hostName("mymac@company.com")
                        .ipAddress("116.0.10.1")
                        .macAddress("02:01:5e:00:53:aa")
                        .operatingSystem(OperatingSystem.builder()
                                .name("Ventura")
                                .version("13.3")
                                .build()
                        ).build()
                ).build();
        return request;
    }
}
