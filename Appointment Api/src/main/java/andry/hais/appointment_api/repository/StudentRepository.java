package andry.hais.appointment_api.repository;

import andry.hais.appointment_api.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
