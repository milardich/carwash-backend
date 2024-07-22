package com.sm.carwashmonitor.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "station")
@Getter
@Setter
@EqualsAndHashCode
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "station_sequence")
    @SequenceGenerator(name="station_sequence", allocationSize = 1)
    @Column(name = "station_id")
    private Long stationId;

    @Column(name = "station_name")
    private String stationName;

    @Column(name = "city")
    private String city;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @Column(name = "country")
    private String country;

    @JsonManagedReference
    @OneToMany(mappedBy = "station")
    private List<Unit> units;
}
