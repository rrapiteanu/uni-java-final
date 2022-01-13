package com.telemed.mapper;
import com.telemed.dto.MessageDto;
import com.telemed.model.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageDto mapToDto(Message message);
    Message mapToEntity(MessageDto messageDto);
}