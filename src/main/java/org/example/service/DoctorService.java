package org.example.service;

import org.example.entity.Doctor;
import org.example.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public List<Doctor> getAll() {
        return repo.findAll();
    }

    public Doctor getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctorul nu exista!"));
    }

    public Doctor save(Doctor d) {

        if (d.getName() == null || d.getName().isEmpty())
            throw new RuntimeException("Numele este obligatoriu!");

        if (d.getSpecialization() == null || d.getSpecialization().isEmpty())
            throw new RuntimeException("Specializarea este obligatorie!");

        return repo.save(d);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new RuntimeException("Doctorul nu exista!");
        repo.deleteById(id);
    }

    // custom
    public List<Doctor> getBySpecialization(String specialization) {
        return repo.findBySpecialization(specialization);
    }

    public List<Doctor> searchByName(String name) {
        return repo.findByNameContaining(name);
    }
}