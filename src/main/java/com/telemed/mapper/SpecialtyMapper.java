package com.telemed.mapper;
import com.telemed.dto.SpecialtyDto;
import com.telemed.model.Specialty;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {
    SpecialtyDto mapToDto(Specialty specialty);
    Specialty mapToEntity(SpecialtyDto specialtyDto);
}