package com.telemed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "prescription_items")
public class PrescriptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer days;

    @Column
    private Integer quantity;

    @Column
    private Integer interval;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescriptionId")
    @JsonIgnore
    private Prescription prescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicamentId")
    private Medicament medicament;
}