package com.telemed.service;
import com.telemed.dto.NewPrescriptionItemRequest;
import com.telemed.dto.NewPrescriptionRequest;
import com.telemed.model.*;
import com.telemed.repository.*;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionItemRepository prescriptionItemRepository;
    private final MedicRepository medicRepository;
    private final PacientRepository pacientRepository;
    private final MedicamentRepository medicamentRepository;


    public PrescriptionService(PrescriptionRepository prescriptionRepository, PrescriptionItemRepository prescriptionItemRepository,
                               MedicRepository medicRepository, PacientRepository pacientRepository, MedicamentRepository medicamentRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionItemRepository =  prescriptionItemRepository;
        this.medicRepository = medicRepository;
        this.pacientRepository = pacientRepository;
        this.medicamentRepository = medicamentRepository;
    }

    public List<Prescription> getPrescriptionsForPacientId(Long id) {
        Pacient pacient = pacientRepository.findPacientById((id));
        if (pacient == null) {
            throw new NoSuchElementException("No patient with id %d found".formatted(id));
        }

        List<Prescription> prescriptions = prescriptionRepository.findPrescriptionsByPacient(pacient);
        return prescriptions;
    }

    public Prescription newPrescription(NewPrescriptionRequest request) {
        Medic medic = medicRepository.findMedicById(request.getMedicId());
        Pacient pacient = pacientRepository.findPacientById(request.getPacientId());

        if(medic == null || pacient == null){
            throw new NoSuchElementException("No medic or patient found");
        }

        Prescription prescription = new Prescription();
        prescription.setPacient(pacient);
        prescription.setMedic(medic);
        prescription.setCreatedAt(OffsetDateTime.now());
        Prescription saved = prescriptionRepository.save(prescription);
        return saved;
    }

    public Prescription addItemToPrescription(NewPrescriptionItemRequest request, Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findPrescriptionById(prescriptionId);
        if(prescription == null){
            throw new NoSuchElementException("No prescription found");
        }

        Medicament medicament = medicamentRepository.findMedicamentById(request.getMedicamentId());

        if(medicament == null){
            throw new NoSuchElementException("Invalid medicament");
        }

        PrescriptionItem newEntry = new PrescriptionItem();
        newEntry.setPrescription(prescription);
        newEntry.setDays(request.getDays());
        newEntry.setInterval(request.getInterval());
        newEntry.setQuantity(request.getQuantity());
        newEntry.setMedicament(medicament);

        PrescriptionItem saved = prescriptionItemRepository.save(newEntry);
        Prescription updated = prescriptionRepository.findPrescriptionById(prescriptionId);
        return updated;
    }

    public Prescription getOne(Long id) {
        return prescriptionRepository.findPrescriptionById(id);
    }
}