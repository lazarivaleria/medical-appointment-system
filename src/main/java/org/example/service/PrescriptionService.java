package org.example.service;

import org.example.entity.Prescription;
import org.example.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;
import org.example.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository repo;

    public PrescriptionService(PrescriptionRepository repo) {
        this.repo = repo;
    }

    public List<Prescription> getAll() {
        return repo.findAll();
    }

    public Prescription getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reteta nu exista!"));
    }

    public Prescription save(Prescription p) {

        if (p.getMedication() == null || p.getMedication().isEmpty())
            throw new RuntimeException("Medicamentul este obligatoriu!");

        if (p.getDosage() == null || p.getDosage().isEmpty())
            throw new RuntimeException("Dozajul este obligatoriu!");

        if (p.getMedicalRecord() == null)
            throw new RuntimeException("Fisa medicala este obligatorie!");

        return repo.save(p);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new ResourceNotFoundException("Reteta nu exista!");
        repo.deleteById(id);
    }

    // custom
    public List<Prescription> getByMedication(String medication) {
        return repo.findByMedicationContaining(medication);
    }

    public List<Prescription> getByMedicalRecord(Long id) {
        return repo.findByMedicalRecordId(id);
    }
}
