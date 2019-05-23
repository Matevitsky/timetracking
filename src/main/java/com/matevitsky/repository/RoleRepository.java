package com.matevitsky.repository;

import com.matevitsky.entity.Role;

public interface RoleRepository {
    Role findRoleById(Integer id);
}
