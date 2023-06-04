package pl.sda.travelagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.travelagency.dto.TripDto;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //public String adminViev(Model model) {


 /*   @GetMapping
    public String showTrips(Model model) {
        List<TripDto> trips = tripService.getTrips();
        List<TripDto> promotingTrips = trips.stream().filter(TripDto::isPromoted).toList();
        trips = trips.stream().limit(3).toList();

        model.addAttribute("trips", trips);
        model.addAttribute("promotingTrips", promotingTrips);
        return "trips";*/


}
