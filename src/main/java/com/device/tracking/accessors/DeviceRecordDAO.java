package com.device.tracking.accessors;

import com.device.tracking.entities.DeviceRecord;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.*;


@Log4j2
public class DeviceRecordDAO {

    /**
     * In this class we can add the real DB connection.
     */
    private final Map<String, List<DeviceRecord>> dynamoDb;

    public DeviceRecordDAO() {
        dynamoDb = new HashMap<>();
    }

    /**
     * Persist the device record in database sorted by time.
     *
     * @param deviceRecord
     */
    public void save(@NonNull DeviceRecord deviceRecord) {
        log.info("saving device info :: {}", deviceRecord);

        // Just to mimic the no-sql db behavior
        dynamoDb.computeIfAbsent(deviceRecord.getDeviceId(), v -> new ArrayList<>()).add(deviceRecord);
    }

    /**
     * Return latest available device record in DB
     *
     * @param deviceId
     * @return {@link Optional <DeviceRecord>}
     */
    public Optional<DeviceRecord> getLatestDeviceRecord(@NonNull String deviceId) {
        if (!dynamoDb.containsKey(deviceId)) {
            return Optional.empty();
        }
        List<DeviceRecord> deviceRecords = dynamoDb.get(deviceId);
        return Optional.ofNullable(deviceRecords.get(deviceRecords.size() - 1));
    }

    /**
     * Return device record sorted by time
     *
     * @param deviceId
     * @return
     */
    public List<DeviceRecord> getDeviceInfoHistory(@NonNull String deviceId) {
        return dynamoDb.getOrDefault(deviceId, new ArrayList<>());
    }
}
