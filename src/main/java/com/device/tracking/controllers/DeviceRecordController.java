package com.device.tracking.controllers;

import com.device.tracking.entities.api.AddDeviceInfoRequest;
import com.device.tracking.entities.api.AddDeviceInfoResponse;
import com.device.tracking.entities.api.DeviceInfoHistoryResponse;
import com.device.tracking.entities.api.DeviceInfoResponse;
import com.device.tracking.handlers.AddDeviceInfoHandler;
import com.device.tracking.handlers.GetDeviceInfoHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class DeviceRecordController {

    @Autowired
    AddDeviceInfoHandler addDeviceInfoHandler;

    @Autowired
    GetDeviceInfoHandler getDeviceInfoHandler;

    /**
     * API saves the device information record in database. It passes the request to its handler which applies the request
     * validation checks and business logics.
     *
     * @param request {@link AddDeviceInfoRequest}
     * @return {@link AddDeviceInfoResponse}
     */
    @PostMapping("/addDeviceInfo")
    public @ResponseBody AddDeviceInfoResponse addDeviceInfo(@RequestBody AddDeviceInfoRequest request) {
        return addDeviceInfoHandler.handleRequest(request);
    }

    /**
     * For a given device id, API retrieve the latest device information from DB and returns it.
     *
     * @param deviceId
     * @return
     */
    @GetMapping("/getDeviceInfo/{deviceId}")
    public @ResponseBody DeviceInfoResponse getDeviceInfo(@PathVariable String deviceId) {
        return getDeviceInfoHandler.getDeviceInfo(deviceId);
    }

    /**
     * For a given device id, API retrieve all the historical device information from DB and returns it.
     *
     * @param deviceId
     * @return
     */
    @GetMapping("/getDeviceInfoHistory/{deviceId}")
    public @ResponseBody DeviceInfoHistoryResponse getDeviceInfoHistory(@PathVariable String deviceId) {
        return getDeviceInfoHandler.getDeviceInfoHistory(deviceId);
    }
}
