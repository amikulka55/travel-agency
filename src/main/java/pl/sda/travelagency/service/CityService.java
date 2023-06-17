package pl.sda.travelagency.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    private final CountryRepository countryRepository;

    private final TravelRepository travelRepository;

    public CityService(CityRepository cityRepository, CountryRepository countryRepository, TravelRepository travelRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.travelRepository = travelRepository;
    }

    public List<CityDto> getCities() {
        List<CityDto> collect = cityRepository.findAll()
                .stream()
                .map(it -> new CityDto(it.getId(), it.getName(), it.getCountry().getId(), it.getCountry().getName()))
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
        List<Travel> byFromCityOrToCity = travelRepository.findByFromCityOrToCity(id);
        if (byFromCityOrToCity.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

