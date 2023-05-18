package pl.sda.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.travelagency.entity.Airport;
import pl.sda.travelagency.entity.City;
import pl.sda.travelagency.entity.Hotel;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TripDto {
    private Long id;
    private String departureCity;
    private String departureAirport;
    private String arrivalCity;
    private String arrivalAirport;
    private String hotel;
    private String startDate;
    private String endDate;
    private Integer days;
    private Double adultPrice;
    private Double childPrice;
    private boolean isPromoted;
    private Integer numberOfAdultPlaces;
    private Integer numberOfChildPlaces;


}
