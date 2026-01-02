package com.jrb.auth_service.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record LogoutResponseDTO(
        @NotBlank String message) {

}
