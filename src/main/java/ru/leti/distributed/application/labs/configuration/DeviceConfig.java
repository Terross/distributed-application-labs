package ru.leti.distributed.application.labs.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "device")
public class DeviceConfig {
    private String comment;
}
