package com.matevitsky.entity;


import java.util.Objects;

public class User {


    private Integer id;

    private String name;

    private String email;

    private String password;

    private Role role;

    private User() {
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public class Builder {
        private Builder() {
        }

        public User.Builder withId(Integer id) {
            User.this.id = id;
            return this;
        }

        public User.Builder withName(String name) {
            User.this.name = name;
            return this;
        }

        public User.Builder withEmail(String email) {
            User.this.email = email;
            return this;
        }

        public User.Builder withPassword(String password) {
            User.this.password = password;
            return this;

        }

        public User.Builder withRole(Role role) {
            User.this.role = role;
            return this;
        }

        public User build() {
            return User.this;
        }

    }
}
