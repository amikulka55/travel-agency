package pl.sda.travelagency.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.travelagency.dto.TripOrderDto;
import pl.sda.travelagency.entity.TripOrder;
import pl.sda.travelagency.repository.TripOrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripOrderService {

    private final TripOrderRepository tripOrderRepository;
    public List<TripOrderDto> showTrips() {
        List<TripOrder> all = tripOrderRepository.findAll();
        return null;
    }


}
