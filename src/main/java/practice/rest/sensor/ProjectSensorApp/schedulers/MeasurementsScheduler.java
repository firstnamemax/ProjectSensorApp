package practice.rest.sensor.ProjectSensorApp.schedulers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import practice.rest.sensor.ProjectSensorApp.models.Measurement;
import practice.rest.sensor.ProjectSensorApp.models.Sensor;
import practice.rest.sensor.ProjectSensorApp.services.MeasurementService;
import practice.rest.sensor.ProjectSensorApp.services.SensorService;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MeasurementsScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementsScheduler.class);

    private final MeasurementService measurementService;
    private final SensorService sensorService;

    @Scheduled(cron = "30 * * * * ?", zone = "Europe/Moscow")
    public void testMethod() {
        LOGGER.info("Scheduler is running");

        String nameSensor = "Sensor1";

        if (sensorService.findSensor(nameSensor).isPresent()){
            for (int i=0; i<5; i++) {
                sendMeasurementToDatabase(nameSensor);
            }
        } else LOGGER.warn("The sensor " + nameSensor + " was not registered");
    }

    public void sendMeasurementToDatabase(String nameSensor){
        Random random = new Random();
        double maxTemperature = 45.0;

        Sensor sensor = new Sensor();
        sensor.setName(nameSensor);

        Measurement measurement = new Measurement();
        measurement.setValue(random.nextDouble() * maxTemperature);
        measurement.setRaining(random.nextBoolean());
        measurement.setSensor(sensor);

        measurementService.save(measurement);
    }

}