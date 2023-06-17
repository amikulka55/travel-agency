package pl.sda.travelagency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.sda.travelagency.entity.Trip;

import java.util.List;

public interface TripRepository extends JpaRepository <Trip, Long>{

    @Query("SELECT t FROM Trip t order by t.departureDate desc")
    List<Trip> findAllOrderByDateFromDesc();

    @Query("SELECT t FROM Trip t WHERE t.departureCity.id = ?1 OR t.arrivalCity.id =?1")
    List<Trip> findByFromCityOrToCity(long id);

    @Query("SELECT t FROM Trip t WHERE t.arrivalCity.id =?1")
    List<Trip> findByFromToArrivalCity(long id);
}
