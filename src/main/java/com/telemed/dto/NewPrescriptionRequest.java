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
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPrescriptionRequest {
    @NotNull(message = "pacientId must not be null")
    private Long pacientId;

    @NotNull(message = "medicId must not be null")
    private Long medicId;
}