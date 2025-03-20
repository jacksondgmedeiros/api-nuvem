package me.dio.api_nuvem.service;

import me.dio.api_nuvem.model.User;

public interface UserService {
    User findById(Long id);

    User create(User user);
}
