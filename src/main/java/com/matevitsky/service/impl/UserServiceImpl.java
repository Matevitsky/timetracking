package com.matevitsky.service.impl;


import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.repository.interfaces.UserRepository;
import com.matevitsky.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean createUser(User user) throws ErrorException {

        return userRepository.create(user);

    }

    @Override
    public boolean deleteUser(Integer userId) {
        return userRepository.deleteById(userId);

    }

    @Override
    public User updateUser(User user) {

        return userRepository.update(user);

    }

    @Override
    public Optional<User> getUserById(Integer id) {

        return userRepository.getById(id);

    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws ErrorException {

        return userRepository.findUserByEmail(email);

    }


}
