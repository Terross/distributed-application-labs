package ru.leti.distributed.application.labs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.leti.distributed.application.labs.domain.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final MessageSource messageSource;
    private final List<Device> devices = new ArrayList<>();

    public String createDevice(Device device, Locale locale) {
        devices.add(device);
        return messageSource.getMessage("devices.create.message", null, locale)
                .formatted(device.getId().toString(), device.getName());
    }

    public List<Device> getAllDevices() {
        return devices;
    }

    public Device getDeviceById(UUID deviceId) {
        return findDeviceById(deviceId);
    }

    public String deleteDeviceById(UUID deviceId, Locale locale) {
        var removedDevice = findDeviceById(deviceId);

        devices.remove(removedDevice);
        return messageSource.getMessage("devices.delete.message", null, locale)
                .formatted(removedDevice.getId().toString(), removedDevice.getName());
    }

    public String updateDevice(UUID deviceId, Device newDevice, Locale locale) {
        var device = findDeviceById(deviceId);
        device.setType(newDevice.getType());
        device.setName(newDevice.getName());
        device.setStatus(newDevice.getStatus());
        device.setMaker(newDevice.getMaker());
        device.setColor(newDevice.getColor());
        return messageSource.getMessage("devices.update.message", null, locale)
                .formatted(device.getId().toString(), device.getName());
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
