package com.Covid_19Patient_Management.Thesis.repository;

import com.Covid_19Patient_Management.Thesis.models.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
    Optional<Nurse> findById(Long id);
    List<Nurse> findAll();
    @Query(value = "SELECT * FROM Nurse", nativeQuery = true)
    List<Nurse> findAllNurse();
    @Modifying
    @Query(value = "INSERT INTO Nurse(name, phone, user_id) VALUES(:name, :phone, :id)", nativeQuery = true)
    @Transactional
    void createNurse(@Param("name")String name, @Param("phone") String phone, @Param("id") Long id);
    @Modifying
    @Query(value = "Update Nurse set name = :name, phone = :phone where id = :id", nativeQuery = true)
    @Transactional
    void alterNurse(@Param("name")String name, @Param("phone") String phone, @Param("id") Long id);
    @Query(value = "SELECT * From nurse where user_id = :user_id", nativeQuery = true)
    Optional<Nurse> findNurseByUserID(@Param("user_id") Long id);
    @Modifying
    @Query(value = "Update Nurse set doctor = :doctor where id = :id", nativeQuery = true)
    @Transactional
    void assignNurseToDoctor(@Param("doctor")Long doctor, @Param("id") Long id);

    @Query(value = "SELECT * from nurse where doctor = :doctor_id", nativeQuery = true)
    List<Nurse> findNurseOfDoctor(@Param("doctor_id") Long doctor_id);
    @Query(value = "SELECT * from nurse where doctor IS :doctor_id", nativeQuery = true)
    List<Nurse> findUnassignedOfDoctor(@Param("doctor_id") Long doctor_id);
    @Modifying
    @Query(value = "Update Nurse set doctor = :doctor where id = :id", nativeQuery = true)
    @Transactional
    void cancelAssignNurseToDoctor(@Param("doctor")Long doctor, @Param("id") Long id);
}
