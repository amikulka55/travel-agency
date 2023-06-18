package pl.sda.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.travelagency.entity.TripOrder;

import java.util.List;

public interface TripOrderRepository extends JpaRepository<TripOrder, Long> {

@Query ("SELECT t FROM TripOrder t ")
    List<TripOrder> tripsFromDB();
}
