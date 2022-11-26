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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final MessageSource messageSource;

    @PostMapping("/device")
    public ResponseEntity<String> createDevice(@RequestBody Device device,
                                               @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return ResponseEntity.ok(deviceService.createDevice(device, locale));
    }

    @GetMapping("/device/{id}/{name}")
    public ResponseEntity<Device> getDevice(@PathVariable("id") UUID id,
                                            @PathVariable("name") String name,
                                            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        Device device = deviceService.getDeviceById(id);
        device.add(linkTo(methodOn(DeviceController.class)
                        .getDevice(id, name, locale))
                        .withSelfRel(),
                linkTo(methodOn(DeviceController.class)
                        .createDevice(new Device(id, name, null, null, null, null, null), locale))
                        .withRel(messageSource.getMessage("devices.create.url", null, locale)),
                linkTo(methodOn(DeviceController.class)
                        .updateDevice(id, new Device(id, name, device.getColor(), device.getType(), device.getMaker(), device.getStatus(), device.getComment()), locale))
                        .withRel(messageSource.getMessage("devices.update.url", null, locale)),
                linkTo(methodOn(DeviceController.class)
                        .deleteDevice(id, locale))
                        .withRel(messageSource.getMessage("devices.delete.url", null, locale)));
        return ResponseEntity.ok(device);
    }

    @GetMapping("/device")
    public ResponseEntity<List<Device>> getDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @DeleteMapping("/device/{id}")
    public ResponseEntity<String> deleteDevice(@PathVariable("id") UUID id,
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
