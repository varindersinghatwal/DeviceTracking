package com.device.tracking.configuration;

import com.device.tracking.accessors.DeviceRecordDAO;
import com.device.tracking.handlers.AddDeviceInfoHandler;
import com.device.tracking.handlers.GetDeviceInfoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DeviceRecordDAO getDeviceinfoDAO() {
        return new DeviceRecordDAO();
    }

    @Bean
    public AddDeviceInfoHandler getAddDeviceInfoHandler() {
        return new AddDeviceInfoHandler(getDeviceinfoDAO());
    }

    @Bean
    public GetDeviceInfoHandler getDeviceInfoHandler() {
        return new GetDeviceInfoHandler(getDeviceinfoDAO());
    }
}
