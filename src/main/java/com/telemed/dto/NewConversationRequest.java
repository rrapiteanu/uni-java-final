package com.telemed.dto;

import com.telemed.model.Author;
import com.telemed.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewConversationRequest {
    @NotNull
    private Long pacientId;

    @NotNull
    private Long medicId;
}