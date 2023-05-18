package pl.sda.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityDto {
    private Long id;
    private String name;
    private Long countryId;
}
