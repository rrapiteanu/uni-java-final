package com.telemed.dto;

import com.telemed.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String text;

    @NotNull
    private OffsetDateTime createdAt;

    private Long conversationId;
    private Long medicId;
    private Long pacientId;
}