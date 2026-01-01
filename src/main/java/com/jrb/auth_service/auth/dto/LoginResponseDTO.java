package com.jrb.auth_service.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(
        @NotBlank String email,
        @NotBlank String token,
        @NotBlank String message) {

}
