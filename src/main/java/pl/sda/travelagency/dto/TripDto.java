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


@NoArgsConstructor
@Data
public class TripDto {
    private Long id;
    private String departureCity;
    private String departureAirport;
    private String arrivalCity;
    private String arrivalAirport;
    private String hotel;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Integer days;
    private Double adultPrice;
    private Double childPrice;
    private boolean isPromoted;
    private Integer numberOfAdultPlaces;
    private Integer numberOfChildPlaces;

    public TripDto( String departureCity,String arrivalCity, LocalDate departureDate, LocalDate arrivalDate, boolean isPromoted) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.isPromoted = isPromoted;

    }
}
