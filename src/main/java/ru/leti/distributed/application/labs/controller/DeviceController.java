package ru.leti.distributed.application.labs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.leti.distributed.application.labs.domain.Device;
import ru.leti.distributed.application.labs.service.DeviceService;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/device")
    public ResponseEntity<String> createDevice(@RequestBody Device device,
                                               @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(deviceService.createDevice(device, locale));
    }

    @GetMapping("/device/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable("id")UUID id) {
        return ResponseEntity.ok(deviceService.getDeviceById(id));
    }

    @GetMapping("/device")
    public ResponseEntity<List<Device>> getDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @DeleteMapping("/device/{id}")
    public ResponseEntity<String> deleteDevice(@PathVariable("id")UUID id,
                                               @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(deviceService.deleteDeviceById(id, locale));
    }

    @PutMapping("/device/{id}")
    public ResponseEntity<String> updateDevice(@PathVariable("id") UUID id,
                                               @RequestBody Device device,
                                               @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(deviceService.updateDevice(id, device, locale));
    }

}
