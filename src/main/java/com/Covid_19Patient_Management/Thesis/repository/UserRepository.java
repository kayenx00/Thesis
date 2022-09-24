package com.Covid_19Patient_Management.Thesis.repository;

import com.Covid_19Patient_Management.Thesis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
//    @Query(value = "SELECT u.id FROM USER u WHERE u.username = ?1")
//    List<Long> findByUserName(String username);
    @Query(value = "SELECT * FROM users WHERE verification_code = :verification_code", nativeQuery = true)
    User findByVerificationCode(@Param("verification_code") String verificationCode);
}
