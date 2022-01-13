package com.telemed.mapper;
import com.telemed.dto.PacientDto;
import com.telemed.model.Pacient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PacientMapper {
    PacientDto mapToDto(Pacient pacient);
    Pacient mapToEntity(PacientDto pacientDto);
}