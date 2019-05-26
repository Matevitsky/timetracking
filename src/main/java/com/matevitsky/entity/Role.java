package com.matevitsky.entity;

import java.util.Objects;

public class Role {
    private final Integer id;
    private final String name;


    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;

    }

    public Role(Integer id) {
        this.id = id;
        name = "";
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
