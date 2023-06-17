package pl.sda.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.travelagency.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
}
