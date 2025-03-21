package me.dio.api_nuvem.service.impl;

import me.dio.api_nuvem.model.User;
import me.dio.api_nuvem.repository.UserRepository;
import me.dio.api_nuvem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        // o retorno caso não encontre, lança a exception
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User user) {
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            throw new IllegalArgumentException(" User already exists!");
        }

        if (userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new IllegalArgumentException(" Conta já existe!");
        }

        return userRepository.save(user);
    }
}
