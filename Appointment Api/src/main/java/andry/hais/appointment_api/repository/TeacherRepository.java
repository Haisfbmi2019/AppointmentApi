package andry.hais.appointment_api.repository;


import andry.hais.appointment_api.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
