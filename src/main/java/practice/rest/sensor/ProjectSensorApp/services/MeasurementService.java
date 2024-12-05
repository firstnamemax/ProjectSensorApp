package practice.rest.sensor.ProjectSensorApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.rest.sensor.ProjectSensorApp.models.Measurement;
import practice.rest.sensor.ProjectSensorApp.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setSensor(sensorService.findSensor(measurement.getSensor().getName()).get());
        measurement.setDate(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    @Transactional(readOnly = true)
    public List<Measurement> findAll(){
        return measurementRepository.findAll(Sort.by("id"));
    }

    @Transactional(readOnly = true)
    public long findRaining(){
        return measurementRepository.countByRaining(true);
    }

}