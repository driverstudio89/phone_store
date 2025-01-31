package com.exercise.phone_store.data;

import com.exercise.phone_store.model.Role;
import com.exercise.phone_store.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(UserRole role);
}
