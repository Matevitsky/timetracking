package com.matevitsky.service;


import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean createUser(User user) throws ErrorException;

    boolean deleteUser(Integer userId);

    User updateUser(User user);

    Optional<User> getUserById(Integer id);

    List<User> getAll();

    User findUserByEmail(String email) throws ErrorException;

}
