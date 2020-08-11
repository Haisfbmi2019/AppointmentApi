package andry.hais.appointment_api.repository;

import andry.hais.appointment_api.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAll();

    List<Schedule> findAllByDateFromBeforeAndDateToAfter(LocalDateTime dateFrom, LocalDateTime dateTo);
}
