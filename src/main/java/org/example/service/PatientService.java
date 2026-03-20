package org.example.service;

import org.example.entity.Patient;
import org.example.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public List<Patient> getAll() {
        return repo.findAll();
    }

    public Patient getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pacientul nu exista!"));
    }

    public Patient save(Patient p) {

        if (p.getName() == null || p.getName().isEmpty())
            throw new RuntimeException("Numele este obligatoriu!");

        if (p.getEmail() == null || !p.getEmail().contains("@"))
            throw new RuntimeException("Email invalid!");

        if (p.getPhone() == null || p.getPhone().length() < 5)
            throw new RuntimeException("Telefon invalid!");

        return repo.save(p);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new RuntimeException("Pacientul nu exista!");
        repo.deleteById(id);
    }

    // custom
    public List<Patient> searchByName(String name) {
        return repo.findByNameContaining(name);
    }

    public Patient getByEmail(String email) {
        return repo.findByEmail(email);
    }
}