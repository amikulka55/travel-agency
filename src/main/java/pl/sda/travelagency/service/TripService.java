package pl.sda.travelagency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.travelagency.dto.TripDto;
import pl.sda.travelagency.entity.Trip;
import pl.sda.travelagency.repository.TripRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {
public final TripRepository tripRepository;
    public List<TripDto> getTripsByFilter(String fromCity, String toCity, String dateFrom, String dateTo) {
        List<TripDto> trips = getTrips();
        if (fromCity != null && !fromCity.isEmpty()) {
            trips = trips.stream().filter(it -> it.getDepartureCity().equals(fromCity)).collect(Collectors.toList());
        }
        if (toCity != null && !toCity.isEmpty()) {
            trips = trips.stream().filter(it -> it.getArrivalCity()
                    .equals(toCity)).collect(Collectors.toList());
        }
        if (dateFrom != null && !dateFrom.isEmpty()) {
            String[] split = dateFrom.split("-");
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
                    return tripDto;
                })
                .limit(5)
                .collect(Collectors.toList());
    }
}
