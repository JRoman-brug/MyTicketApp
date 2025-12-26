package com.jrb.auth_service.user;

import org.springframework.data.repository.CrudRepository;

import com.jrb.auth_service.user.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}