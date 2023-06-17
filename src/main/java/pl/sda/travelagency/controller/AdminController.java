package pl.sda.travelagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.travelagency.dto.*;
import pl.sda.travelagency.service.*;


import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TripService tripService;
    private final ContinentService continentService;
    private final CountryService countryService;
    private final CityService cityService;
    private final HotelService hotelService;
    private final AirportService airportService;
    private CreateTripDto createTripDto;

    public AdminController(TripService tripService, ContinentService continentService, CountryService countryService, CityService cityService, HotelService hotelService,
                           AirportService airportService) {
        this.tripService = tripService;
        this.continentService = continentService;
        this.countryService = countryService;
        this.cityService = cityService;
        this.hotelService = hotelService;
        this.airportService = airportService;
    }

    @GetMapping
    public String travels(Model model) {
        List<TripDto> travels = tripService.getTrips();
        model.addAttribute("travels", travels);
        return "admin/travels";
    }

    @PostMapping("/travel/delete/{id}")
    public String delete(Model model, @PathVariable long id ) {
        tripService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/travel/add")
    public String addTrip(Model model) {
        model.addAttribute("createTrip", new CreateTripDto());
        model.addAttribute("cities", cityService.getCities());
        return "admin/add-travel";
    }

    @GetMapping("/travel/edit/{id}")
    public String tripEdit(Model model, @PathVariable long id ) {
        TripDto travel = tripService.getTripById(id);
        model.addAttribute("trip", travel);
        return "admin/edit-trip";
    }

    @PostMapping(value = "/travel/add")
    public String postBody(@ModelAttribute CreateTripDto createTravelDto, Model model) {
        tripService.createTrip(createTripDto);
        return "redirect:/admin";
    }

    @GetMapping("/continents")
    public String continents(Model model) {
        List<ContinentDto> continents = continentService.getContinents();
        model.addAttribute("continents", continents);
        model.addAttribute("newContinent", new ContinentDto());
        return "admin/continents";
    }

    @PostMapping("/continents/add")
    public String addContinent(Model model,@ModelAttribute ContinentDto continentDto) {
        continentService.addContinent(continentDto);
        return "redirect:/admin/continents";
    }

    @PostMapping("/continents/delete/{id}")
    public String deleteContinent(Model model, @PathVariable long id ) {
        continentService.deleteById(id);
        return "redirect:/admin/continents";
    }

    @GetMapping("/countries")
    public String countries(Model model) {
        List<ContinentDto> continents = continentService.getContinents();
        List<CountryDto> countries = countryService.getCountries();
        model.addAttribute("continents", continents);
        model.addAttribute("countries", countries);
        model.addAttribute("newCountry", new CountryDto());
        return "admin/countries";
    }

    @PostMapping("/countries/delete/{id}")
    public String deleteCountry(Model model, @PathVariable long id ) {
        countryService.deleteById(id);
        return "redirect:/admin/countries";
    }

    @PostMapping("/country/add")
    public String addCountry(Model model,@ModelAttribute CountryDto countryDto) {
        countryService.addCountry(countryDto);
        return "redirect:/admin/countries";
    }

    @GetMapping("/cities")
    public String cities(Model model, @RequestParam(required = false) String cityValidation) {
        List<CountryDto> countries = countryService.getCountries();
        List<CityDto> cities = cityService.getCities();
        if (cityValidation!=null && cityValidation.equals("false")) {
            model.addAttribute("cityCanBeDelete", "false");
        }
        model.addAttribute("cities", cities);
        model.addAttribute("countries", countries);
        model.addAttribute("newCity", new CityDto());
        return "admin/cities";
    }

    @PostMapping("/city/add")
    public String addCity(Model model,@ModelAttribute CityDto cityDto) {
        cityService.addCity(cityDto);
        return "redirect:/admin/cities";
    }

    @PostMapping("/city/delete/{id}")
    public String deleteCity(Model model, @PathVariable long id ) {
        boolean validationResult = cityService.checkCityCanBeDelete(id);
        if (!validationResult) {
            return "redirect:/admin/cities?cityValidation=false";
        } else {
            cityService.deleteById(id);
            return "redirect:/admin/cities";
        }
    }

    @GetMapping("/hotels")
    public String hotels(Model model, @RequestParam(required = false) String hotelValidation) {
        List<HotelDto> hotels = hotelService.getHotels();
        List<CityDto> cities = cityService.getCities();
        if (hotelValidation!=null && hotelValidation.equals("false")) {
            model.addAttribute("hotelCanBeDelete", "false");
        }
        model.addAttribute("hotels", hotels);
        model.addAttribute("cities", cities);
        model.addAttribute("newHotel", new HotelDto());
        return "admin/hotels";
    }

    @PostMapping("/hotel/add")
    public String addHotel(Model model,@ModelAttribute HotelDto hotelDto) {
        hotelService.addHotel(hotelDto);
        return "redirect:/admin/hotels";
    }

    @PostMapping("/hotel/delete/{id}")
    public String deleteHotel(@PathVariable long id ) {
        boolean validationResult = hotelService.checkHotelCanBeDelete(id);
        if (!validationResult) {
            return "redirect:/admin/hotels?hotelValidation=false";
        } else {
            hotelService.deleteById(id);
            return "redirect:/admin/hotels";
        }
    }

    @GetMapping("/airports")
    public String airports(Model model) {
        List<AirportDto> airports = airportService.getAirports();
        List<CityDto> cities = cityService.getCities();
        model.addAttribute("airports", airports);
        model.addAttribute("cities", cities);
        model.addAttribute("newAirport", new AirportDto());
        return "admin/airports";
    }

    @PostMapping("/airport/add")
    public String addAirport(Model model,@ModelAttribute AirportDto airportDto) {
        airportService.addAirport(airportDto);
        return "redirect:/admin/airports";
    }

    @PostMapping("/airport/delete/{id}")
    public String deleteAirport(Model model, @PathVariable long id ) {
        airportService.deleteById(id);
        return "redirect:/admin/airports";
    }
}
