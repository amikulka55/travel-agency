package pl.sda.travelagency.service;

import org.springframework.stereotype.Service;
import pl.sda.travelagency.dto.AirportDto;
import pl.sda.travelagency.entity.Airport;
import pl.sda.travelagency.entity.City;
import pl.sda.travelagency.repository.AirportRepository;
import pl.sda.travelagency.repository.CityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    private final CityRepository cityRepository;

    public AirportService(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }


    public List<AirportDto> getAirports() {
        List<AirportDto> collect = airportRepository.findAll()
                .stream()
                .map(it -> new AirportDto(it.getId(), it.getName(), it.getCity().getId()))
                .collect(Collectors.toList());
        return collect;
    }

    public void addAirport(AirportDto airportDto) {
        Airport airport = new Airport();
        airport.setName(airportDto.getName());
        Optional<City> byId = cityRepository.findById(airportDto.getCityId());
        byId.ifPresent(it -> {
            airport.setCity(byId.get());
            airportRepository.save(airport);
        });
    }
    public void deleteById(long id){
        airportRepository.deleteById(id);
    }
}

