package ru.leti.distributed.application.labs.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.leti.distributed.application.labs.domain.Device;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends CrudRepository<Device, UUID> {

    List<Device> findAll();
}
