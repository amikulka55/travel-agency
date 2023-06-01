package pl.sda.travelagency.service;

import org.springframework.stereotype.Service;
import pl.sda.travelagency.dto.SimpleParticipantDto;
import pl.sda.travelagency.dto.TripOrderDto;
import pl.sda.travelagency.entity.Trip;
import pl.sda.travelagency.entity.TripOrder;
import pl.sda.travelagency.repository.TripOrderRepository;
import pl.sda.travelagency.repository.TripRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ReservationService {


    private final TripOrderRepository tripOrderRepository;
    private final TripRepository tripRepository;
    public ReservationService(TripOrderRepository tripOrderRepository, TripRepository tripRepository) {
        this.tripOrderRepository = tripOrderRepository;
        this.tripRepository = tripRepository;
    }


    public double calculateCost(TripOrderDto tripOrderDto, long tripId) {
        Optional<Trip> byId = tripRepository.findById(tripId);
        double totalCost = 0;
        if (byId.isPresent()) {
            for (SimpleParticipantDto simpleParticipantDto : tripOrderDto.getSimpleParticipantDtos()) {
                if (simpleParticipantDto.getAdult() == Boolean.FALSE) {
                    totalCost = totalCost + byId.get().getChildPrice();
                } else if (simpleParticipantDto.getAdult() == Boolean.TRUE) {
                    totalCost = totalCost + byId.get().getAdultPrice();
                }
            }
        }
        return totalCost;
    }

    public void createReservation(TripOrderDto tripOrderDto, long tripId) {
        Optional<Trip> byId = tripRepository.findById(tripId);
        byId.ifPresent(t -> {
            TripOrder tripOrder = new TripOrder();
            tripOrder.setTrip(t);
            tripOrder.setBuyerEmail(tripOrderDto.getBuyerEmail());
            tripOrder.setBuyerPhone(tripOrderDto.getBuyerPhone());
            tripOrder.setBuyerFirstName(tripOrderDto.getBuyerFirstName());
            tripOrder.setBuyerLastName(tripOrderDto.getBuyerLastName());
            List<String> strings = tripOrderDto.getSimpleParticipantDtos().stream().map(elem -> elem.getFirstName() + " " + elem.getLastName()).toList();
            String participants = String.join(",", strings);
            tripOrder.setParticipants(participants);
            tripOrderRepository.save(tripOrder);
        });

    }

}
