package com.matevitsky.repository.interfaces;

import com.matevitsky.entity.Role;

public interface RoleRepository {
    Role findRoleById(Integer id);
}
