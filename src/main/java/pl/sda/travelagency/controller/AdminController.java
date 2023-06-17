package pl.sda.travelagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.travelagency.dto.TripOrderDto;
import pl.sda.travelagency.repository.TripOrderRepository;
import pl.sda.travelagency.service.TripOrderService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private TripOrderService tripOrderService;


    @GetMapping
    public String showTrips(Model model) {
        List<TripOrderDto> trips = tripOrderService.showTrips();
       // List<TripOrderDto>


    }

    ;

}
