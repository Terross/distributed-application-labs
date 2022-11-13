package ru.leti.distributed.application.labs.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.leti.distributed.application.labs.domain.Device;
import ru.leti.distributed.application.labs.service.DeviceService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping("/device")
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        return ResponseEntity.ok(deviceService.createDevice(device));
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
    public ResponseEntity<String> deleteDevice(@PathVariable("id")UUID id) {
        deviceService.deleteDeviceById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/device/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable("id") UUID id,
                                               @RequestBody Device device) {
        return ResponseEntity.ok(deviceService.updateDevice(id, device));
    }

}
