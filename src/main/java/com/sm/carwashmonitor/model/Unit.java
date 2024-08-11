package com.sm.carwashmonitor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sm.carwashmonitor.model.enumeration.UnitStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "unit")
@Getter
@Setter
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_sequence")
    @SequenceGenerator(name="unit_sequence", allocationSize = 1)
    @Column(name = "unit_id")
    private Long unitId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    @Column(name = "status")
    private String status;

    @JsonManagedReference
    @OneToMany(mappedBy = "unit")
    private List<WashCycle> washCycles;
}
