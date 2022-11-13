package ru.leti.distributed.application.labs.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Device {

    private UUID id;
    private String name;
    private String color;
    private String type;
    private String maker;
    private boolean status;
}
