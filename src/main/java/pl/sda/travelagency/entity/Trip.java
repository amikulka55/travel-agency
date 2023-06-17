package pl.sda.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlIDREF;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Trip  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageName;
    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(name="arrival_city_id")
    private City arrivalCity;
    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;
    @OneToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelId;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate departureDate;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate arrivalDate;
    private Integer days;
    private boolean isPromoted;
    private Double adultPrice;
    private Double childPrice;
    private Integer numberOfAdultPlaces;
    private Integer numberOfChildPlaces;
}
