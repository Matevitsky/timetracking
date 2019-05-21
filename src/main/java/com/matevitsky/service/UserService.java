package com.matevitsky.service;


import com.matevitsky.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User insertUser(User user);

    User deleteUser(User user);

    User updateUser(User user);

    Optional<User> getUser(Integer id);

    List<User> getAll();

    Optional<User> findUserByEmail(String email);

}
