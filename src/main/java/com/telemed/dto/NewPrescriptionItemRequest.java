package com.telemed.dto;

import com.telemed.model.Author;
import com.telemed.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPrescriptionItemRequest {
    @NotNull(message = "days must not be null")
    @Min(value = 1, message = "days should not be less than 1")
    private Integer days;

    @NotNull(message = "quantity must not be null")
    @Min(value = 1, message = "quantity should not be less than 1")
    private Integer quantity;

    @NotNull(message = "interval must not be null")
    @Min(value = 1, message = "interval should not be less than 1")
    @Max(value = 24, message = "interval should not be greater than 24")
    private Integer interval;

    @NotNull(message = "medicamentId must not be null")
    private Long medicamentId;
}