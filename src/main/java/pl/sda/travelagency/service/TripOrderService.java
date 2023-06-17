package pl.sda.travelagency.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.travelagency.dto.TripDto;
import pl.sda.travelagency.dto.TripOrderDto;
import pl.sda.travelagency.entity.Trip;
import pl.sda.travelagency.entity.TripOrder;
import pl.sda.travelagency.repository.TripOrderRepository;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class TripOrderService {

    public final  TripOrderRepository tripOrderRepository;
    public List<TripOrderDto> getOrderedTrips(){
        List<TripOrder> orderedTrips = tripOrderRepository.tripsFromDB();
        return orderedTrips.stream()
                .map(it -> {
                    TripOrderDto tripOrderDto = new TripOrderDto();
                    tripOrderDto.getSimpleParticipantDtos();
                    tripOrderDto.getBuyerPhone();
                    tripOrderDto.getBuyerEmail();
                    tripOrderDto.getBuyerFirstName();
                    tripOrderDto.getBuyerLastName();
                    tripOrderDto.getSum();

                    return tripOrderDto;
                })
                        .limit(10)
                        .collect(Collectors.toList());
    }

 /*   public List<TripDto> getTrips() {
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
    }*/
}
