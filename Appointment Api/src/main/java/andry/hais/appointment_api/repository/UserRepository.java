package andry.hais.appointment_api.repository;

import andry.hais.appointment_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
