package com.telemed.service;

import com.telemed.dto.AddMedicRequest;
import com.telemed.dto.MedicDto;
import com.telemed.dto.SpecialtyDto;
import com.telemed.mapper.MedicMapper;
import com.telemed.model.Author;
import com.telemed.model.Medic;
import com.telemed.model.Specialty;
import com.telemed.repository.AuthorRepository;
import com.telemed.repository.MedicRepository;
import com.telemed.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MedicService {

    private final MedicRepository medicRepository;
    private final SpecialtyRepository specialtyRepository;
    private final AuthorRepository authorRepository;
    private final MedicMapper medicMapper;

    public MedicService(MedicRepository medicRepository, SpecialtyRepository specialtyRepository, AuthorRepository authorRepository, MedicMapper medicMapper) {
        this.medicRepository = medicRepository;
        this.specialtyRepository = specialtyRepository;
        this.authorRepository = authorRepository;
        this.medicMapper = medicMapper;
    }

    public List<MedicDto> getAllMedics() {
        List<Medic> medics = medicRepository.findAll();

        return medics.stream().map(m -> medicMapper.mapToDto(m)).collect(Collectors.toList());
    }

    public List<MedicDto> getMedicsBySpecialtyId(Long id) {
        Specialty specialty = specialtyRepository.findSpecialtyById(id);
        if (specialty == null) {
            throw new NoSuchElementException("No specialty with id %d found".formatted(id));
        }

        List<Medic> medics = medicRepository.findAllBySpecialty(specialty);
        return medics.stream().map(m -> medicMapper.mapToDto(m)).collect(Collectors.toList());
    }

    public MedicDto add(AddMedicRequest request) {
        Specialty specialty = specialtyRepository.findSpecialtyById(request.getSpecialtyId());
        if (specialty == null) {
            throw new NoSuchElementException("No specialty with id %d found".formatted(request.getSpecialtyId()));
        }

        Medic medic = new Medic();
        medic.setFirstName(request.getFirstName());
        medic.setLastName(request.getLastName());
        medic.setEmail(request.getEmail());
        medic.setSpecialty(specialty);
        Author authorProfile = new Author();
        authorProfile.setIsPacient(false);
        Author savedAuthorProfile = authorRepository.save(authorProfile);
        medic.setAuthorProfile(savedAuthorProfile);
        Medic saved = medicRepository.save(medic);
        return medicMapper.mapToDto(saved);
    }

    public MedicDto getOne(Long id) {
        return medicMapper.mapToDto(medicRepository.findMedicById(id));
    }
}