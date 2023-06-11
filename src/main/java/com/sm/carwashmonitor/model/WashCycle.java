package com.sm.carwashmonitor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "wash_cycle")
@Getter
@Setter
public class WashCycle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wash_cycle_sequence")
    @SequenceGenerator(name="wash_cycle_sequence", allocationSize = 1)
    @Column(name = "wash_cycle_id")
    private Long washCycleId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "wash_cycle_date")
    private LocalDateTime washCycleDate;

    @Column(name = "water_consumption")
    private Float waterConsumption;

    @Column(name = "detergent_consumption")
    private Float detergentConsumption;

    @Column(name = "wax_consumption")
    private Float waxConsumption;

    @Column(name = "coin_amount")
    private Float coinAmount;
}
