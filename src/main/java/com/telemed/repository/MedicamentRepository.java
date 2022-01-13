package com.telemed.repository;

import com.telemed.model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicamentRepository extends  JpaRepository<Medicament, Integer> {
    Medicament findMedicamentById(Long id);
    List<Medicament> findAll();
}


