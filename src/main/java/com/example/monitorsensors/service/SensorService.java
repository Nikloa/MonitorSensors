package com.example.monitorsensors.service;

import com.example.monitorsensors.model.SensorEntity;
import com.example.monitorsensors.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorService {

    private static final ExampleMatcher SEARCH_CONDITIONS_MATCH_ANY = ExampleMatcher
            .matchingAny()
            .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("model", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("location", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
            .withIgnorePaths("id", "range", "type", "unit");

    @Autowired
    SensorRepository sensorRepository;

    public List<SensorEntity> list() {
        return sensorRepository.findAll();
    }

    public SensorEntity create(SensorEntity sensorEntity) {
        return sensorRepository.save(sensorEntity);
    }

    public SensorEntity read(long id) {
        return sensorRepository.findSensorEntityById(id);
    }

    public boolean update(long id, SensorEntity sensorEntity) {
        if (sensorRepository.existsById(id)) {
            sensorEntity.setId(id);
            sensorRepository.save(sensorEntity);
            return true;
        }
        return false;
    }

    public boolean delete(long id) {
        if (sensorRepository.existsById(id)) {
            sensorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<SensorEntity> search(String search) {

        List<SensorEntity> list = new ArrayList<>();

        for (String word : search.split(" ")) {
            list.addAll(sensorRepository.findAllByNameContainingIgnoreCaseOrModelContainingIgnoreCaseOrLocationContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                    word, word, word, word
            ));
        }

        return list.stream().distinct().collect(Collectors.toList());
    }
}
