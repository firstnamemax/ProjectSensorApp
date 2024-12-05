package practice.rest.sensor.ProjectSensorApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import practice.rest.sensor.ProjectSensorApp.models.Measurement;
import practice.rest.sensor.ProjectSensorApp.services.SensorService;

@Component
public class SensorValidatorForSaveMeasurement implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidatorForSaveMeasurement(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Measurement.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Measurement measurement = (Measurement) o;

        if (measurement.getSensor() == null)
            return;

        if (sensorService.findSensor(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "This sensor is not registered");
        }
    }

}