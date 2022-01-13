package com.telemed.mapper;
import com.telemed.dto.MedicDto;
import com.telemed.model.Medic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicMapper {
    MedicDto mapToDto(Medic medic);
    Medic mapToEntity(MedicDto medicDto);
}