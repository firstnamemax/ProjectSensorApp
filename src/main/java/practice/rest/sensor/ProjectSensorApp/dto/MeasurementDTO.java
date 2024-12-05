package practice.rest.sensor.ProjectSensorApp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import practice.rest.sensor.ProjectSensorApp.models.Sensor;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDTO {

    @Min(value = -100, message = "Value must be greater than -100")
    @Max(value = 100, message = "Value must be less than 100")
    @NotNull(message = "Value should not be empty")
    private Double value;

    @NotNull(message = "Raining should not be empty")
    private Boolean raining;

    @NotNull(message = "Sensor should not be empty")
    private SensorDTO sensor;

}