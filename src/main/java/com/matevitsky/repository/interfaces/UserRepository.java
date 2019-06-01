package com.matevitsky.repository.interfaces;


import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;

import java.util.Optional;

public interface UserRepository extends GenericRepository<User> {
    Optional<User> findUserByEmail(String email) throws ErrorException;
}
