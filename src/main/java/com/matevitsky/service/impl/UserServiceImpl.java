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
    public boolean insertUser(User user) {
        return userRepository.create(user);

    }

    @Override
    public boolean deleteUser(Integer userId) {
        return userRepository.delete(userId);

    }

    @Override
    public User updateUser(User user) {
        //TODO: проверить кто вызывает и что лучше вернуть user или boolean

        return (User) userRepository.update(user);

    }

    @Override
    public Optional<User> getUser(Integer id) {

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
