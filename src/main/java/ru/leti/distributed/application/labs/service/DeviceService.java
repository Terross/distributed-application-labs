package ru.leti.distributed.application.labs.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.leti.distributed.application.labs.domain.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class DeviceService {

    private final List<Device> devices = new ArrayList<>();

    public Device createDevice(Device device) {
        devices.add(device);
        return device;
    }

    public List<Device> getAllDevices() {
        return devices;
    }

    public Device getDeviceById(UUID deviceId) {
        return findDeviceById(deviceId);
    }

    public void deleteDeviceById(UUID deviceId) {
        var removedDevice = findDeviceById(deviceId);

        devices.remove(removedDevice);
    }

    public Device updateDevice(UUID deviceId, Device newDevice) {
        var device = findDeviceById(deviceId);
        device.setType(newDevice.getType());
        device.setName(newDevice.getName());
        device.setStatus(newDevice.isStatus());
        device.setMaker(newDevice.getMaker());
        device.setColor(newDevice.getColor());
        return device;
    }


    private Device findDeviceById(UUID deviceId) {
        return devices
                .stream()
                .filter(device -> device.getId().equals(deviceId))
                .findFirst()
                .orElseThrow(() -> {
                            log.error("Device with id = %s not found".formatted(deviceId.toString()));
                            throw new RuntimeException("Device not found");
                        }
                );
    }

}
