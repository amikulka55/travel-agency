package pl.sda.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.travelagency.entity.Trip;
import pl.sda.travelagency.entity.User;

import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TripPurchaseDto {
    private Long id;
    private String tripPurchase;
    private String userPurchase;
    private double amount;
}
