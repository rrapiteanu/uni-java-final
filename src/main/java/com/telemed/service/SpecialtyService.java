package com.telemed.service;

import com.telemed.dto.SpecialtyDto;
import com.telemed.mapper.SpecialtyMapper;
import com.telemed.model.Specialty;
import com.telemed.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;
    private final SpecialtyMapper specialtyMapper;

    public SpecialtyService(SpecialtyRepository specialtyRepository, SpecialtyMapper specialtyMapper) {
        this.specialtyRepository = specialtyRepository;
        this.specialtyMapper = specialtyMapper;
    }

    public List<SpecialtyDto> getAllSpecialties() {
        List<Specialty> specialties = specialtyRepository.findAll();

        return specialties.stream().map(m -> specialtyMapper.mapToDto(m)).collect(Collectors.toList());
    }

    public SpecialtyDto add(SpecialtyDto specialtyDto) {
        Specialty specialty = specialtyMapper.mapToEntity(specialtyDto);
        Specialty saved = specialtyRepository.save(specialty);
        return  specialtyMapper.mapToDto(saved);
    }

    public SpecialtyDto getOne(Long id) {
        return specialtyMapper.mapToDto(specialtyRepository.findSpecialtyById(id));
    }


    public SpecialtyDto update(SpecialtyDto specialtyDto, Long id) {
        Specialty specialty = specialtyRepository.findSpecialtyById(id);

        Specialty updated = specialtyMapper.mapToEntity(specialtyDto);
        specialty.setDenumire(updated.getDenumire());

        Specialty saved = specialtyRepository.save(specialty);
        return specialtyMapper.mapToDto(saved);
    }
}