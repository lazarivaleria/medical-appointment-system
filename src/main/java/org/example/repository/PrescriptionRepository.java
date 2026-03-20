package org.example.repository;

import org.example.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    // cautare dupa medicament
    List<Prescription> findByMedicationContaining(String medication);

    // retete pentru o fisa medicala
    List<Prescription> findByMedicalRecordId(Long medicalRecordId);
}
