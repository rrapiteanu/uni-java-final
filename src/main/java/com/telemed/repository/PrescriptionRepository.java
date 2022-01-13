package com.telemed.repository;

import com.telemed.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrescriptionRepository extends  JpaRepository<Prescription, Integer> {
    Prescription findPrescriptionById(Long id);
    List<Prescription> findPrescriptionsByMedic(Medic medic);
    List<Prescription> findPrescriptionsByPacient(Pacient pacient);
    List<Prescription> findPrescriptionByMedicAndPacient(Medic medic, Pacient pacient);
}