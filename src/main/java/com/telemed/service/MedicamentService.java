package com.telemed.service;

import com.telemed.model.Medicament;
import com.telemed.dto.MedicamentDto;

import com.telemed.mapper.MedicamentMapper;
import com.telemed.repository.MedicamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentService {

    private final MedicamentRepository medicamentRepository;
    private final MedicamentMapper medicamentMapper;

    public MedicamentService(MedicamentRepository medicamentRepository, MedicamentMapper medicamentMapper) {
        this.medicamentRepository = medicamentRepository;
        this.medicamentMapper = medicamentMapper;
    }

    public List<MedicamentDto> getAllMedicamente() {
        List<Medicament> medicamente = medicamentRepository.findAll();

        return medicamente.stream().map(m -> medicamentMapper.mapToDto(m)).collect(Collectors.toList());
    }

    public MedicamentDto add(MedicamentDto medicamentDto) {
        Medicament medicament = medicamentMapper.mapToEntity(medicamentDto);
        Medicament saved = medicamentRepository.save(medicament);
        return  medicamentMapper.mapToDto(saved);
    }

    public MedicamentDto getOne(Long id) {
        return medicamentMapper.mapToDto(medicamentRepository.findMedicamentById(id));
    }


    public MedicamentDto update(MedicamentDto medicamentDto, Long id) {
        Medicament medicament = medicamentRepository.findMedicamentById(id);

        Medicament updated = medicamentMapper.mapToEntity(medicamentDto);
        medicament.setDenumire(updated.getDenumire());

        Medicament saved = medicamentRepository.save(medicament);
        return medicamentMapper.mapToDto(saved);
    }
}