package com.jrb.auth_service.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
                @NotBlank String firstname,
                @NotBlank String lastname,
                @NotBlank String email,
                @NotBlank @Size(min = 6) String password) {

}
