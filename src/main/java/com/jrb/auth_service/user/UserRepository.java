package com.jrb.auth_service.user;

import org.springframework.data.repository.CrudRepository;

import com.jrb.auth_service.user.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    boolean existsByEmail(String email);
}