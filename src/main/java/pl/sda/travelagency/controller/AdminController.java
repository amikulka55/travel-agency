package pl.sda.travelagency.controller;


import lombok.AllArgsConstructor;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import pl.sda.travelagency.dto.TripOrderDto;
import pl.sda.travelagency.repository.TripOrderRepository;
import pl.sda.travelagency.service.TripOrderService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {



public final TripOrderService tripOrderService;
public final TripOrderRepository tripOrderRepository;
@GetMapping
    public String tripsForAdmin(Model model){
    List <TripOrderDto> allTrips = tripOrderService.getOrderedTrips();

    model.addAttribute("allTrips", allTrips);
    return "admin";


}

}