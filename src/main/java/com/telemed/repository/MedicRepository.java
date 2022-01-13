package com.telemed.repository;

import com.telemed.model.Medic;
import com.telemed.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicRepository extends  JpaRepository<Medic, Integer> {
    Medic findMedicById(Long id);
    List<Medic> findAllBySpecialty(Specialty specialty);
    List<Medic> findAll();
}


