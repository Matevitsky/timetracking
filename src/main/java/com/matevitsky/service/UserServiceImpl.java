package com.matevitsky.service;


import com.matevitsky.entity.User;
import com.matevitsky.repository.db.UserRepository;
import com.matevitsky.repository.db.UserRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {


    UserRepository userRepository = new UserRepositoryImpl();


    @Override
    public User insertUser(User user) {
        userRepository.insertEntity(user);
        return user;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        return userRepository.deleteEntity(userId);

    }

    @Override
    public User updateUser(User user) {
        //TODO: проверить кто вызывает и что лучше вернуть user или boolean

        return (User) userRepository.updateEntity(user);

    }

    @Override
    public Optional<User> getUser(Integer id) {

        return userRepository.getEntity(id);

    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {

        return userRepository.findUserByEmail(email);

    }

    //:TODO boolean is login(String email, String password){
    //TODO: validate email and password искать только по мэйлу. вытащить юзера и сравнить его пароль с тем что ввел юзер

}
