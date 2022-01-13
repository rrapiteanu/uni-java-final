

package com.telemed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {
    @NotNull
    private Long id;

    @NotNull
    private Boolean isPacient;
}