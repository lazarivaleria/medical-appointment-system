package org.example.repository;

import org.example.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    // cautare dupa diagnostic
    List<MedicalRecord> findByDiagnosisContaining(String diagnosis);
}
