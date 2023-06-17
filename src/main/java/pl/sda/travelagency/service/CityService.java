package pl.sda.travelagency.service;

import org.springframework.stereotype.Service;
import pl.sda.travelagency.dto.CityDto;
import pl.sda.travelagency.entity.City;
import pl.sda.travelagency.entity.Country;
import pl.sda.travelagency.entity.Trip;
import pl.sda.travelagency.repository.CityRepository;
import pl.sda.travelagency.repository.CountryRepository;
import pl.sda.travelagency.repository.TripRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    private final TripRepository tripRepository;

    public CityService(CityRepository cityRepository, CountryRepository countryRepository, TripRepository tripRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.tripRepository = tripRepository;
    }

    public List<CityDto> getCities() {
        List<CityDto> collect = cityRepository.findAll()
                .stream()
                .map(it -> new CityDto(it.getId(), it.getName(), it.getCountry().getId()))
                .collect(Collectors.toList());
        return collect;
    }

    public void addCity(CityDto cityDto) {
        City city = new City();
        city.setName(cityDto.getName());
        Optional<Country> byId = countryRepository.findById(cityDto.getCountryId());
        byId.ifPresent(it -> {
            city.setCountry(byId.get());
            cityRepository.save(city);
        });
    }
    public void deleteById(long id){
        cityRepository.deleteById(id);
    }

    public boolean checkCityCanBeDelete(long id) {
        List<Trip> byFromCityOrToCity = tripRepository.findByFromCityOrToCity(id);
        if (byFromCityOrToCity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

