package org.example.repository;

import org.example.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    // cautare dupa nume
    List<Patient> findByNameContaining(String name);

    // cautare dupa email
    Patient findByEmail(String email);
}
