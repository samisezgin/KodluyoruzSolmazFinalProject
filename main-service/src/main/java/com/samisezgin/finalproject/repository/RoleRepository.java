package com.samisezgin.finalproject.repository;

import com.samisezgin.finalproject.model.Role;
import com.samisezgin.finalproject.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
