package pl.sda.travelagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.travelagency.dto.SimpleParticipantDto;
import pl.sda.travelagency.dto.TripDto;
import pl.sda.travelagency.dto.TripOrderDto;
import pl.sda.travelagency.service.ReservationService;
import pl.sda.travelagency.service.TripService;

import java.util.ArrayList;
@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final TripService tripService;

    private final ReservationService reservationService;

    public ReservationController(TripService tripService, ReservationService reservationService) {
        this.tripService = tripService;
        this.reservationService = reservationService;
    }

    @GetMapping("/{tripId}")
    public String reserveView(Model model, @PathVariable long tripId) {
        TripOrderDto tripOrderDto = new TripOrderDto();
        tripOrderDto.setSimpleParticipantDtos(new ArrayList<>());
        TripDto tripById = tripService.getTripById(tripId);
        model.addAttribute("tripOrderDto", tripOrderDto);
        model.addAttribute("trip", tripById);
        return "reservation";
    }
    @PostMapping("/make-reservation/{tripId}")
    public String reservation(@ModelAttribute TripOrderDto tripOrderDto, @PathVariable long tripId, Model model){
        reservationService.createReservation(tripOrderDto, tripId);
        return "reservation-summary";
    }

    @PostMapping("/{tripId}")
    public String reservation(@ModelAttribute TripOrderDto tripOrderDto, Model model, @RequestParam(value = "action", required = false) String action, @PathVariable long tripId ){
        boolean delete = action != null && action.startsWith("delete");
        TripDto tripById = tripService.getTripById(tripId);
        if ( delete) {
            String substring = action.substring(6, 7);
            int i = Integer.parseInt(substring);
            SimpleParticipantDto simpleParticipantDto = tripOrderDto.getSimpleParticipantDtos().get(i);
            tripOrderDto.getSimpleParticipantDtos().remove(simpleParticipantDto);
            model.addAttribute("tripOrderDto", tripOrderDto);
            model.addAttribute("trip", tripById);
            return "reservation";
        } else if (action!= null && action.equals("save")) {
            double totalCost = reservationService.calculateCost(tripOrderDto, tripId);

            model.addAttribute("totalCost", totalCost + tripById.getAdultPrice());
            tripOrderDto.setCost(totalCost + tripById.getAdultPrice());
            model.addAttribute("tripOrderDto", tripOrderDto);
            model.addAttribute("trip", tripById);
            return "summary";
        } else {
            if (tripOrderDto.getSimpleParticipantDtos() == null) {
                tripOrderDto.setSimpleParticipantDtos(new ArrayList<>());
            }
            tripOrderDto.getSimpleParticipantDtos().add(new SimpleParticipantDto());

            model.addAttribute("tripOrderDto", tripOrderDto);
            model.addAttribute("trip", tripById);
            return "reservation";
        }

    }

}
