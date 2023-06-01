package pl.sda.travelagency.dto;

import lombok.Data;

@Data
public class SimpleParticipantDto {
    String firstName;
    String lastName;
    Boolean adult = true;
}
