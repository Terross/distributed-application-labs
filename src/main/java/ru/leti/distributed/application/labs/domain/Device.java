package ru.leti.distributed.application.labs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "device")
public class Device extends RepresentationModel<Device> {

    @Id
    private UUID id;
    private String name;
    private String color;
    private String type;
    private String maker;
    private Boolean status;
    private String comment;

    public Device withComment(String comment) {
        this.setComment(comment);
        return this;
    }
}
