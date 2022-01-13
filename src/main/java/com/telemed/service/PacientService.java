package com.telemed.service;
import com.telemed.dto.AddPacientRequest;
import com.telemed.dto.PacientDto;
import com.telemed.mapper.PacientMapper;
import com.telemed.model.Author;
import com.telemed.model.Pacient;
import com.telemed.repository.AuthorRepository;
import com.telemed.repository.PacientRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacientService {

    private final PacientRepository pacientRepository;
    private final AuthorRepository authorRepository;
    private final PacientMapper pacientMapper;

    public PacientService(PacientRepository pacientRepository, AuthorRepository authorRepository, PacientMapper pacientMapper) {
        this.pacientRepository = pacientRepository;
        this.authorRepository = authorRepository;
        this.pacientMapper = pacientMapper;
    }

    public List<PacientDto> getAll() {
        List<Pacient> pacienti = pacientRepository.findAll();
        return pacienti.stream().map(p -> pacientMapper.mapToDto(p)).collect(Collectors.toList());
    }

    public PacientDto add(AddPacientRequest request) {
        Pacient pacient = new Pacient();
        pacient.setFirstName(request.getFirstName());
        pacient.setLastName(request.getLastName());
        pacient.setEmail(request.getEmail());
        pacient.setGender(request.getGender());
        pacient.setWeight(request.getWeight());
        pacient.setHeight(request.getHeight());

        Author authorProfile = new Author();
        authorProfile.setIsPacient(true);
        Author savedAuthorProfile = authorRepository.save(authorProfile);
        pacient.setAuthorProfile(savedAuthorProfile);
        Pacient saved = pacientRepository.save(pacient);
        return pacientMapper.mapToDto(saved);
    }

    public PacientDto getOne(Long id) {
        return pacientMapper.mapToDto(pacientRepository.findPacientById(id));
    }
}