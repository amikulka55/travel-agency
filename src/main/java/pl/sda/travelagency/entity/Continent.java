package pl.sda.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Continent {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

private String name;

    @OneToMany(mappedBy = "continent")
    private List<Country> countries;
}
