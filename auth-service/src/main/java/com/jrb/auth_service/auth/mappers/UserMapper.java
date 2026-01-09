package com.jrb.auth_service.auth.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.jrb.auth_service.auth.dto.RegisterRequestDTO;
import com.jrb.auth_service.user.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    public UserEntity registerDtoToUser(RegisterRequestDTO dto);
}
