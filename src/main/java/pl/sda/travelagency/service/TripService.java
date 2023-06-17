package pl.sda.travelagency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.travelagency.dto.CreateTripDto;
import pl.sda.travelagency.dto.TripDto;
import pl.sda.travelagency.entity.Airport;
import pl.sda.travelagency.entity.City;
import pl.sda.travelagency.entity.Hotel;
import pl.sda.travelagency.entity.Trip;
import pl.sda.travelagency.repository.AirportRepository;
import pl.sda.travelagency.repository.CityRepository;
import pl.sda.travelagency.repository.HotelRepository;
import pl.sda.travelagency.repository.TripRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final CityRepository cityRepository;
    private final AirportRepository airportRepository;
    private final HotelRepository hotelRepository;

    public TripService(CityRepository cityRepository, AirportRepository airportRepository, HotelRepository hotelRepository, TripRepository tripRepository) {
        this.cityRepository = cityRepository;
        this.airportRepository = airportRepository;
        this.hotelRepository = hotelRepository;
        this.tripRepository = tripRepository;
    }

    TripDto tripDto = new TripDto();

    public List<TripDto> getTripsByFilter(String departureCity, String arrivalCity, String departureDate, String
            arrivalDate) {
        List<TripDto> trips = getTrips();
        if (departureCity != null && !departureCity.isEmpty()) {
            trips = trips.stream().filter(it -> it.getDepartureCity().equals(departureCity)).collect(Collectors.toList());
        }
        if (arrivalCity != null && !arrivalCity.isEmpty()) {
            trips = trips.stream().filter(it -> it.getArrivalCity()
                    .equals(arrivalCity)).collect(Collectors.toList());
        }
        if (arrivalDate != null && !arrivalDate.isEmpty()) {
            String[] split = arrivalDate.split("-");
            LocalDate of = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            trips = trips.stream().filter(it -> it.getDepartureDate().isAfter(of)).toList();
        }
        return trips;
    }

    public List<TripDto> getTrips() {
        List<Trip> all = tripRepository.findAllOrderByDateFromDesc();
        return all.stream()
                .map(it -> {
                    TripDto tripDto = new TripDto(it.getDepartureCity().getName(), it.getArrivalCity().getName(), it.getDepartureDate(), it.getArrivalDate(), it.isPromoted());
                    tripDto.setAdultPrice(it.getAdultPrice());
                    tripDto.setId(it.getId());
                    tripDto.setImageName(it.getImageName());
                    return tripDto;
                })
                .limit(5)
                .collect(Collectors.toList());
    }

    public TripDto getTripById(long id) {
        Optional<Trip> byId = tripRepository.findById(id);
        TripDto tripDto = new TripDto();
        byId.ifPresent(it -> {
            tripDto.setId(it.getId());
            tripDto.setDepartureCity(it.getDepartureCity().getName());
            tripDto.setArrivalCity(it.getArrivalCity().getName());
            tripDto.setDepartureAirport(it.getDepartureAirport().getName());
            tripDto.setArrivalAirport(it.getArrivalAirport().getName());
            tripDto.setDepartureDate(it.getDepartureDate());
            tripDto.setArrivalDate(it.getArrivalDate());
            tripDto.setHotel(it.getHotelId().getName());
            tripDto.setAdultPrice(it.getAdultPrice());
            tripDto.setChildPrice(it.getChildPrice());
        });
        return tripDto;
    }

    public void delete(long id) {
        tripRepository.deleteById(id);
    }

    CreateTripDto createTripDto = new CreateTripDto();

    public void createTrip(CreateTripDto createTripDto) {
        Optional<City> departureCity = cityRepository.findById(createTripDto.getDepartureCity());
        Optional<Airport> departureAirport = airportRepository.findById(createTripDto.getDepartureAirport());
        Optional<City> arrivalCity = cityRepository.findById(createTripDto.getArrivalCity());
        Optional<Airport> arrivalAirport = airportRepository.findById(createTripDto.getArrivalAirport());


        Trip trip = new Trip();
        trip.setImageName(createTripDto.getImageName());
        trip.setDepartureCity(departureCity.get());
        trip.setArrivalAirport(arrivalAirport.get());
        trip.setArrivalCity(arrivalCity.get());
        trip.setDepartureAirport(departureAirport.get());


        String[] split = createTripDto.getArrivalDate().toString().split("/");
        LocalDate arrivalDate = LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0]));
        trip.setArrivalDate(arrivalDate);
        trip.setAdultPrice(createTripDto.getAdultPrice());
        trip.setChildPrice(createTripDto.getChildPrice());
        trip.setNumberOfAdultPlaces(createTripDto.getNumberOfAdultPlaces());
        trip.setNumberOfChildPlaces(createTripDto.getNumberOfChildPlaces());

        if (createTripDto.getIsPromoted() == null) {
            trip.setPromoted(false);
        } else {
            trip.setPromoted(createTripDto.getIsPromoted());
        }

        tripRepository.save(trip);
    }
}


