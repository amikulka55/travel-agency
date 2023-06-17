package pl.sda.travelagency.service;

import org.springframework.stereotype.Service;
import pl.sda.travelagency.dto.HotelDto;
import pl.sda.travelagency.entity.City;
import pl.sda.travelagency.entity.Hotel;
import pl.sda.travelagency.entity.Trip;
import pl.sda.travelagency.repository.CityRepository;
import pl.sda.travelagency.repository.HotelRepository;
import pl.sda.travelagency.repository.TripRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    private final CityRepository cityRepository;
    private final TripRepository tripRepository;

    public HotelService(HotelRepository hotelRepository, CityRepository cityRepository, TripRepository tripRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
        this.tripRepository = tripRepository;
    }


    public List<HotelDto> getHotels() {
        List<HotelDto> collect = hotelRepository.findAll()
                .stream()
                .map(it -> new HotelDto(it.getId(), it.getName(), it.getRating(), it.getCity().getId()))
                .collect(Collectors.toList());
        return collect;
    }

    public void addHotel(HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        Optional<City> byId = cityRepository.findById(hotelDto.getCityId());
        byId.ifPresent(it -> {
            hotel.setCity(byId.get());
            hotelRepository.save(hotel);
        });
    }
    public void deleteById(long id){
        hotelRepository.deleteById(id);
    }

    public boolean checkHotelCanBeDelete(long id) {
        List<Trip> byToHotel = tripRepository.findByFromToArrivalCity(id);
        return byToHotel.isEmpty();
    }
}

