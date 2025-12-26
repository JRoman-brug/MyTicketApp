package com.jrb.auth_service.repository;

import org.springframework.data.repository.CrudRepository;

import com.jrb.auth_service.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
}