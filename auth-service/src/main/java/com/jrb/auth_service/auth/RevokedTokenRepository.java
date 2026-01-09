package com.jrb.auth_service.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jrb.auth_service.auth.entity.RevokeTokenEntity;

@Repository
public interface RevokedTokenRepository extends CrudRepository<RevokeTokenEntity, String> {

}
