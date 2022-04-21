package com.project.rpgstoreback.repository;

import com.project.rpgstoreback.models.Role;
import com.project.rpgstoreback.models.RoleEnum;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(RoleEnum role);
}
