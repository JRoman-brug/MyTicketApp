package com.jrb.auth_service.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jrb.auth_service.user.UserRepository;
import com.jrb.auth_service.user.entity.UserEntity;

// The difference between UserDetailsServices and UserDetailsManager is that UserDetailsServices is more general that UserDetailsManager
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repository.findByEmail(username);
        return User.builder().username(user.getEmail()).password(user.getPassword()).build();
    }

}
