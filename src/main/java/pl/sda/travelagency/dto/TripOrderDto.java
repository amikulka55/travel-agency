package pl.sda.travelagency.dto;

import lombok.Data;

import java.util.List;

@Data
public class TripOrderDto {
    public List<SimpleParticipantDto> simpleParticipantDtos;
    String buyerFirstName;
    String buyerLastName;
    String buyerPhone;
    String buyerEmail;
    double cost;
}
