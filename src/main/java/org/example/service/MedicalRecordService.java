package org.example.service;

import org.example.entity.MedicalRecord;
import org.example.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;
import org.example.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository repo;

    public MedicalRecordService(MedicalRecordRepository repo) {
        this.repo = repo;
    }

    public List<MedicalRecord> getAll() {
        return repo.findAll();
    }

    public MedicalRecord getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fisa medicala nu exista!"));
    }

    public MedicalRecord save(MedicalRecord m) {

        if (m.getDiagnosis() == null || m.getDiagnosis().isEmpty())
            throw new RuntimeException("Diagnosticul este obligatoriu!");

        if (m.getAppointment() == null)
            throw new RuntimeException("Programarea este obligatorie!");

        return repo.save(m);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Fisa medicala nu exista!");
        repo.deleteById(id);
    }

    // custom
    public List<MedicalRecord> getByDiagnosis(String diagnosis) {
        return repo.findByDiagnosisContaining(diagnosis);
    }
}
