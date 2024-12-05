package practice.rest.sensor.ProjectSensorApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import practice.rest.sensor.ProjectSensorApp.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    long countByRaining(boolean flag);

    @Query(value = "select count(m.id) from Measurement m where m.raining = ?1 AND m.value > ?2")
    int exampleForQuery(boolean flag, double temperature);
}