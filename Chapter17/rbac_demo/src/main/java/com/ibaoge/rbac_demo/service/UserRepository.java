package com.ibaoge.rbac_demo.service;

import com.ibaoge.rbac_demo.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    User save(User user);
}