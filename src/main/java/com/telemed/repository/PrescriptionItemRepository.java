package com.telemed.repository;

import com.telemed.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrescriptionItemRepository extends  JpaRepository<PrescriptionItem, Integer> {
    List<PrescriptionItem> findAllByPrescription(Prescription prescription);
}