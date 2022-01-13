package com.telemed.dto;

import com.telemed.model.Author;
import com.telemed.model.Gender;
import com.telemed.model.Specialty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPacientRequest {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @NotEmpty
    private Gender gender;

    @Min(value = 10, message = "Height should not be less than 10")
    @Max(value = 300, message = "Height should not be greater than 300")
    private Number height;

    @NotNull
    @Min(value = 0, message = "Weight should not be less than 0")
    private Number weight;

    @PastOrPresent
    private Date birthDate;
}