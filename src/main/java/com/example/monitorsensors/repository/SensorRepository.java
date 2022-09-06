package com.example.monitorsensors.repository;

import com.example.monitorsensors.model.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long> {

    SensorEntity findSensorEntityById(long id);
}
