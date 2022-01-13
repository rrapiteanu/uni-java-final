package com.telemed.mapper;
import com.telemed.dto.MedicamentDto;
import com.telemed.model.Medicament;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicamentMapper {
    MedicamentDto mapToDto(Medicament medicament);
    Medicament mapToEntity(MedicamentDto medicamentDto);
}