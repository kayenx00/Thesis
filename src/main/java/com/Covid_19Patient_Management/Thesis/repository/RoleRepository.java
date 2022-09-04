package com.Covid_19Patient_Management.Thesis.repository;

import com.Covid_19Patient_Management.Thesis.models.ERole;
import com.Covid_19Patient_Management.Thesis.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
