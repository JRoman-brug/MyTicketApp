package com.jrb.auth_service.utils.jwt;

import java.util.Date;

public record TokenInfo(
                String jti,
                String email,
                Date issuedAt,
                Date expirationTime

) {

}
