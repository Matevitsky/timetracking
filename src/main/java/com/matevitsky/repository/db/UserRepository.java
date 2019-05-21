package com.matevitsky.repository.db;


import com.matevitsky.entity.User;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findUserByEmail(String email);
}
