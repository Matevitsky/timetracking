package com.matevitsky.service.interfaces;


import com.matevitsky.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User insertUser(User user);

    boolean deleteUser(Integer userId);

    User updateUser(User user);

    Optional<User> getUser(Integer id);

    List<User> getAll();

    Optional<User> findUserByEmail(String email);

}
