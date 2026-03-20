package org.example.repository;

import org.example.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // cautare dupa specializare
    List<Doctor> findBySpecialization(String specialization);

    // cautare dupa nume
    List<Doctor> findByNameContaining(String name);
}