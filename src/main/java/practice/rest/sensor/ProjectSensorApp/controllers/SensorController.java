package practice.rest.sensor.ProjectSensorApp.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import practice.rest.sensor.ProjectSensorApp.dto.SensorDTO;
import practice.rest.sensor.ProjectSensorApp.models.Sensor;
import practice.rest.sensor.ProjectSensorApp.services.SensorService;
import practice.rest.sensor.ProjectSensorApp.util.ErrorResponseMyUtil;
import practice.rest.sensor.ProjectSensorApp.util.SensorNotCreatedException;
import practice.rest.sensor.ProjectSensorApp.util.SensorValidatorForName;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorValidatorForName sensorValidatorForName;

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        sensorValidatorForName.validate(convertToSensor(sensorDTO), bindingResult);

        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }

            throw new SensorNotCreatedException(errorMsg.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponseMyUtil> handleException(SensorNotCreatedException e) {
        ErrorResponseMyUtil response = new ErrorResponseMyUtil(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}