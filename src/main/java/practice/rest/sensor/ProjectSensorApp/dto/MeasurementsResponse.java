package practice.rest.sensor.ProjectSensorApp.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementsResponse {

    List<MeasurementDTO> measurements = new ArrayList<>();

}