package com.jrb.auth_service.auth.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "revoked_tokens")
public class RevokeTokenEntity {
    @Id
    private String id;
    @NotBlank
    private String email;
    @Column(nullable = false)
    private Date revokedAt;

    @Column(nullable = false)
    private Date expiresAt;

}
