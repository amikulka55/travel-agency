package pl.sda.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.travelagency.entity.Hotel;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTripDto {
    private String imageName;
    private long departureCity;
    private long departureAirport;
    private long arrivalCity;
    private long arrivalAirport;
    private Hotel hotelId;
    private LocalDate arrivalDate;
    private Integer days;
    private Double adultPrice;
    private Double childPrice;
    private Boolean isPromoted;

    private int numberOfAdultPlaces;
    private int numberOfChildPlaces;

    public CreateTripDto(String name) {
        this.imageName = imageName;
    }
}
