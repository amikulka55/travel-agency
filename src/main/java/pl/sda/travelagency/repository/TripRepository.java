package pl.sda.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sda.travelagency.entity.Trip;

import java.util.List;

public interface TripRepository extends JpaRepository <Trip, Long>{

    @Query("SELECT t FROM Trip t order by t.departureDate desc")
    List<Trip> findAllOrderByDateFromDesc();
}
