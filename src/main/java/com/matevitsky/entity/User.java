package com.matevitsky.entity;


public class User extends BaseUser {



    public User(Integer id, String name, String email, String password, Role role) {
        super(id, name, email, password, role);

    }

    public User(String name, String email, String password, Role role) {
        super(name, email, password, role);

    }


    @Override
    public String toString() {
        return "User{} " + super.toString();
    }
}
