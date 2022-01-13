package com.telemed.repository;

import com.telemed.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SpecialtyRepository extends  JpaRepository<Specialty, Integer> {
    Specialty findSpecialtyById(Long id);
    List<Specialty> findAll();
}


