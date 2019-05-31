package com.matevitsky.service;


import com.matevitsky.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean insertUser(User user);

    boolean deleteUser(Integer userId);

    User updateUser(User user);

    Optional<User> getUser(Integer id);

    List<User> getAll();

    User findUserByEmail(String email);

}
