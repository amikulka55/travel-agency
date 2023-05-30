package pl.sda.travelagency.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.travelagency.dto.TripDto;
import pl.sda.travelagency.service.TripService;

import java.util.List;

@Controller
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

public final TripService tripService;
    @GetMapping
    public String showTrips(Model model) {
        List<TripDto> trips = tripService.getTrips();
        List<TripDto> promotingTrips = trips.stream().filter(TripDto::isPromoted).toList();
        trips = trips.stream().limit(3).toList();

        model.addAttribute("trips", trips);
        model.addAttribute("promotingTrips", promotingTrips);
        return "trips";
    }
    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(value = "departureCity", required = false) String departureCity,
                         @RequestParam(value = "arrivalCity", required = false) String arrivalCity,
                         @RequestParam(value = "departureDate", required = false) String departureDate,
                         @RequestParam(value = "arrivalDate", required = false) String arrivalDate) {
        List<TripDto> filteredTrips =   tripService.getTripsByFilter(departureCity, arrivalCity, departureDate, arrivalDate);
        model.addAttribute("trips", filteredTrips);
        model.addAttribute("arrivalCity", arrivalCity);
        model.addAttribute("departureCity", departureCity);
        model.addAttribute("departureDate", departureDate);
        model.addAttribute("arrivalDate", arrivalDate);
        return "search";
    }
    @GetMapping("/trip-details/{id}")
    public String tripDetails(Model model, @PathVariable Long id) {
        TripDto tripById = tripService.getTripById(id);
        model.addAttribute("trip", tripById);
        return "/trip-details";
    }
}
