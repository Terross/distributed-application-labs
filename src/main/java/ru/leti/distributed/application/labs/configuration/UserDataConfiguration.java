package ru.leti.distributed.application.labs.configuration;

import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserDataConfiguration {
    @Bean
    InfoContributor getInfoContributor() {
        Map<String, Object> details = new HashMap<>();
        details.put("nameApp", "Devices");
        details.put("developers", "Dmitry Zaykov and Alexandr Panarin");
        details.put("email", "dima3809@gmail.com");
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("info", details);
        return new MapInfoContributor(wrapper);
    }

}
