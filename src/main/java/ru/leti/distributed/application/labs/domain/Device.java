package ru.leti.distributed.application.labs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device extends RepresentationModel<Device> {

    private UUID id;
    private String name;
    private String color;
    private String type;
    private String maker;
    private Boolean status;
}
