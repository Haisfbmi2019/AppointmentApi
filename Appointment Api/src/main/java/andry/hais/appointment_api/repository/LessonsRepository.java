package andry.hais.appointment_api.repository;

import andry.hais.appointment_api.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LessonsRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findAll();

    Lesson findById(Long id);

    @Query("SELECT l FROM Lesson l where l.dateFrom < :newDateTo AND l.dateTo > :newDateFrom")
    List<Lesson> countReservedLessons(LocalDateTime newDateFrom, LocalDateTime newDateTo);
}
