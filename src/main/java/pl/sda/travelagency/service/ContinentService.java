package pl.sda.travelagency.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class ContinentService {
    private final ContinentRepository continentRepository;

    public ContinentService(ContinentRepository continentRepository) {
        this.continentRepository = continentRepository;
    }

    public List<ContinentDto> getContinents() {
        return continentRepository.findAll().stream().map(it -> new ContinentDto(it.getId(), it.getName())).collect(Collectors.toList());
    }

    public void addContinent(ContinentDto continentDto) {
        Continent continent = new Continent();
        continent.setName(continentDto.getName());
        continentRepository.save(continent);
    }

    public void deleteById(long id){
        continentRepository.deleteById(id);
    }
}

