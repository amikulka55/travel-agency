package pl.sda.travelagency.controller;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
=======
>>>>>>> origin/master
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
<<<<<<< HEAD
import pl.sda.travelagency.dto.TripDto;
=======
>>>>>>> origin/master
import pl.sda.travelagency.dto.TripOrderDto;
import pl.sda.travelagency.repository.TripOrderRepository;
import pl.sda.travelagency.service.TripOrderService;

import java.util.List;
<<<<<<< HEAD
@AllArgsConstructor
=======

>>>>>>> origin/master
@Controller
@RequestMapping("/admin")
public class AdminController {

<<<<<<< HEAD

public final TripOrderService tripOrderService;
public final TripOrderRepository tripOrderRepository;
@GetMapping
    public String tripsForAdmin(Model model){
    List <TripOrderDto> allTrips = tripOrderService.getOrderedTrips();

    model.addAttribute("allTrips", allTrips);
    return "admin";


}


 /*   @GetMapping
    public String showTrips(Model model) {
        List<TripDto> trips = tripService.getTrips();
        List<TripDto> promotingTrips = trips.stream().filter(TripDto::isPromoted).toList();
        trips = trips.stream().limit(3).toList();

        model.addAttribute("trips", trips);
        model.addAttribute("promotingTrips", promotingTrips);
        return "trips";*/

}

=======
    private TripOrderService tripOrderService;


    @GetMapping
    public String showTrips(Model model) {
        List<TripOrderDto> trips = tripOrderService.showTrips();
       // List<TripOrderDto>


    }

    ;

}
>>>>>>> origin/master
