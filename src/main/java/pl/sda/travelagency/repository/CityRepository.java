package pl.sda.travelagency.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.travelagency.entity.City;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findById(Long id);
}
