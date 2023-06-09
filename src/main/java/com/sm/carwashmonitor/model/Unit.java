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

    @Column(name = "coin_tray_amount")
    private Integer coinTrayAmount;

    @Column(name = "total_water_consumption")
    private Float totalWaterConsumption;

    @Column(name = "total_detergent_consumption")
    private Float totalDetergentConsumption;

    @Column(name = "total_wax_consumption")
    private Float totalWaxConsumption;

    @JsonManagedReference
    @OneToMany(mappedBy = "unit")
    private List<WashCycle> washCycles;
}
