package com.matevitsky.entity;

import java.util.Objects;

public abstract class BaseUser {

    private final Integer id;

    private final String name;

    private final String Email;

    private final String password;

    private final Role role;


    public BaseUser(Integer id, String name, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        Email = email;
        this.password = password;
        this.role = role;
    }

    public BaseUser(String name, String email, String password, Role role) {
        this.id = 0;
        this.name = name;
        Email = email;
        this.password = password;
        this.role = role;
    }

    public BaseUser() {
        this.id = null;
        this.name = null;
        Email = null;
        this.password = null;
        this.role = null;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "BaseUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Email='" + Email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseUser)) {
            return false;
        }
        BaseUser baseUser = (BaseUser) o;
        return Objects.equals(id, baseUser.id) &&
                Objects.equals(name, baseUser.name) &&
                Objects.equals(Email, baseUser.Email) &&
                Objects.equals(password, baseUser.password) &&
                role == baseUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, Email, password, role);
    }
}
