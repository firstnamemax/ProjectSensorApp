package practice.rest.sensor.ProjectSensorApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.rest.sensor.ProjectSensorApp.models.Sensor;
import practice.rest.sensor.ProjectSensorApp.repositories.SensorRepository;

import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Sensor> findSensor(String name) {
        return sensorRepository.findSensorByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

}