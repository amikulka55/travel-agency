package pl.sda.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.travelagency.entity.TripOrder;

public interface TripOrderRepository extends JpaRepository<TripOrder, Long> {
}
