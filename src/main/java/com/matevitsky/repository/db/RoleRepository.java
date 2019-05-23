package com.matevitsky.repository.db;

import com.matevitsky.entity.Role;

public interface RoleRepository {
    Role findRoleById(Integer id);
}
