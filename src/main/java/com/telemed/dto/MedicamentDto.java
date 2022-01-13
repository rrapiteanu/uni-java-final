package com.telemed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentDto {
    @NotNull
    private Long id;

    @NotNull
    @NotEmpty
    private String denumire;
}