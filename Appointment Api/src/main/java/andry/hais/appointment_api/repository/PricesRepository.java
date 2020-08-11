package andry.hais.appointment_api.repository;

import andry.hais.appointment_api.models.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PricesRepository extends JpaRepository<Prices, Integer> {
    List<Prices> findAll();
}
