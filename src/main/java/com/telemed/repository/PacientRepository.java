package com.telemed.repository;

import com.telemed.model.Pacient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PacientRepository extends  JpaRepository<Pacient, Integer> {
    Pacient findPacientById(Long id);
    List<Pacient> findAll();
}


