package pl.sda.travelagency.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Entity
@Table
@Getter
@Setter
@ToString
public class TripOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    private String participants;

    private double sum;

    private String buyerEmail;

    private String buyerPhone;

    private String buyerFirstName;

    private String buyerLastName;

}
