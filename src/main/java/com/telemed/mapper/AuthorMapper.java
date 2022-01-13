package com.telemed.mapper;
import com.telemed.dto.AuthorDto;
import com.telemed.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDto mapToDto(Author author);
    Author mapToEntity(AuthorDto authorDto);
}