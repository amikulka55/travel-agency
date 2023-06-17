package pl.sda.travelagency.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    private final CityRepository cityRepository;
    private final TravelRepository travelRepository;

    public HotelService(HotelRepository hotelRepository, CityRepository cityRepository, TravelRepository travelRepository) {
        this.hotelRepository = hotelRepository;
        this.cityRepository = cityRepository;
        this.travelRepository = travelRepository;
    }


    public List<HotelDto> getHotels() {
        List<HotelDto> collect = hotelRepository.findAll()
                .stream()
                .map(it -> new HotelDto(it.getId(), it.getName(), it.getCity().getId(), it.getCity().getName(), it.getStarts().label))
                .collect(Collectors.toList());
        return collect;
    }

    public void addHotel(HotelDto hotelDto) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        Star startByLabel = Star.getStartByLabel(hotelDto.getStarts());
        hotel.setStarts(startByLabel);
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
        List<Travel> byToHotel = travelRepository.findByFromToHotel(id);
        return byToHotel.isEmpty();
    }
}

