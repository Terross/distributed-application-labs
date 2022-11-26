package ru.leti.distributed.application.labs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.leti.distributed.application.labs.configuration.DeviceConfig;
import ru.leti.distributed.application.labs.domain.Device;
import ru.leti.distributed.application.labs.repository.DeviceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceService {

    private final MessageSource messageSource;
    private final DeviceRepository deviceRepository;
    private final DeviceConfig deviceConfig;

    public String createDevice(Device device, Locale locale) {
        deviceRepository.save(device);
        return messageSource.getMessage("devices.create.message", null, locale)
                .formatted(device.getId().toString(), device.getName());
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(UUID deviceId) {
        return deviceRepository.findById(deviceId)
                .orElseThrow(() -> new IllegalArgumentException(messageSource
                        .getMessage("devices.search.error.message", null, null)
                        .formatted(deviceId.toString())))
                .withComment(deviceConfig.getComment());
    }

    public String deleteDeviceById(UUID deviceId, Locale locale) {
        var removedDevice = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new IllegalArgumentException(messageSource
                        .getMessage("devices.search.error.message", null, locale)
                        .formatted(deviceId.toString())));

        deviceRepository.delete(removedDevice);
        return getFormattedMessage("devices.delete.message", removedDevice, locale);
    }

    public String updateDevice(UUID deviceId, Device newDevice, Locale locale) {
        deviceRepository.save(newDevice);
        return getFormattedMessage("devices.update.message", newDevice, locale);
    }

    private String getFormattedMessage(String code, Device device, Locale locale) {
        return messageSource.getMessage(code, null, locale)
                .formatted(device.getId().toString(), device.getName());
    }

}
