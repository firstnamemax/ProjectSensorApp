package practice.rest.sensor.ProjectSensorApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import practice.rest.sensor.ProjectSensorApp.models.Sensor;
import practice.rest.sensor.ProjectSensorApp.services.SensorService;

@Component
public class SensorValidatorForName implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidatorForName(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Sensor.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sensor sensor = (Sensor) o;

        if (sensorService.findSensor(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "This sensor has already been registered");
        }
    }

}