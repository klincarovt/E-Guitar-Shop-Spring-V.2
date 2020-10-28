package com.project.eguitarshop.service;

import com.project.eguitarshop.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService  extends UserDetailsService {
    User findById(Long userId);
    User findByName(String userId);
    User registerUser(User user);
    User registerAdmin();
    void deleteById(Long id);
}
