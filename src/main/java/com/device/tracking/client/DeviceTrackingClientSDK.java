package com.device.tracking.client;

import com.device.tracking.entities.api.AddDeviceInfoRequest;
import com.device.tracking.entities.api.AddDeviceInfoResponse;
import com.device.tracking.entities.api.DeviceInfoHistoryResponse;
import com.device.tracking.entities.api.DeviceInfoResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;

import static com.device.tracking.client.SdkConstants.*;

public class DeviceTrackingClientSDK {

    private final ObjectMapper objectMapper;

    public DeviceTrackingClientSDK() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Call 'AddDeviceInfo' api of device tracking service to save the device information
     *
     * @param request
     * @return
     * @throws IOException
     */
    public AddDeviceInfoResponse callAddDeviceInfoRequest(@NonNull final AddDeviceInfoRequest request) throws IOException {
        HttpURLConnection con = createHttpPostConnection(ADD_DEVICE_INFO_API);
        final String jsonInputString = this.objectMapper.writeValueAsString(request);
        final byte[] body = jsonInputString.getBytes(StandardCharsets.UTF_8);
        con.getOutputStream().write(body, 0, body.length);
        final StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return this.objectMapper.readValue(response.toString(), AddDeviceInfoResponse.class);
    }

    /**
     * Get latest device info if available in the database other api return the error message.
     *
     * @param deviceId
     * @return
     * @throws IOException
     */
    public DeviceInfoResponse callGetDeviceInfo(final String deviceId) throws IOException {
        HttpURLConnection con = createHttpGetConnection(MessageFormat.format(GET_DEVICE_INFO_API, deviceId));
        final StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return this.objectMapper.readValue(response.toString(), DeviceInfoResponse.class);
    }

    /**
     * Get all the device info history if available in the database other api return the error message.
     *
     * @param deviceId
     * @return
     * @throws IOException
     */
    public DeviceInfoHistoryResponse callGetDeviceInfoHistory(final String deviceId) throws IOException {
        HttpURLConnection con = createHttpGetConnection(MessageFormat.format(GET_DEVICE_INFO_HISTORY_API, deviceId));
        final StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return this.objectMapper.readValue(response.toString(), DeviceInfoHistoryResponse.class);
    }

    private HttpURLConnection createHttpPostConnection(final String apiName) throws IOException {
        final URL url = new URL(SERVICE_URL + apiName);
        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        return con;
    }

    private HttpURLConnection createHttpGetConnection(final String apiName) throws IOException {
        URL url = new URL(SERVICE_URL + apiName);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        return con;
    }
}
