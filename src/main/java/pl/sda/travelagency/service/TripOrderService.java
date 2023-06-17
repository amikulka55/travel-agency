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

@Service
@RequiredArgsConstructor
public class TripOrderService {

    private final TripOrderRepository tripOrderRepository;
    public List<TripOrderDto> getOrderedTrips(){
        List<TripOrder> all = tripOrderRepository.tripsFromDB();
        return all.stream()
                .map(it-> {
                    TripOrderDto tripOrderDto = new TripOrderDto();
                    tripOrderDto.setBuyerFirstName(it.getBuyerFirstName());
                    tripOrderDto.setBuyerLastName(it.getBuyerLastName());
                    tripOrderDto.setBuyerPhone(it.getBuyerPhone());
                    tripOrderDto.setBuyerEmail(it.getBuyerEmail());
                    tripOrderDto.setCost(it.getSum());
                    return tripOrderDto;
                })
                .collect(Collectors.toList());
    }



}
