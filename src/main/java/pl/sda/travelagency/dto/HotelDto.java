package pl.sda.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDto {
    private Long id;
    private String name;
    private Integer rating;
    private Long cityId;

}
