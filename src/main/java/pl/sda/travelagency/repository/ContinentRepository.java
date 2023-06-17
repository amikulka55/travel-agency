package pl.sda.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.travelagency.entity.Continent;

@Repository
public interface ContinentRepository extends JpaRepository<Continent, Long> {
}
