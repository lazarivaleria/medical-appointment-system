package org.example.controller;

import org.example.entity.Patient;
import org.example.service.PatientService;
import org.example.repository.PatientRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService service;
    private final PatientRepository repo;

    public PatientController(PatientService service, PatientRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping
    public List<Patient> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Patient getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Patient create(@RequestBody Patient p) {
        return service.save(p);
    }

    @PutMapping("/{id}")
    public Patient update(@PathVariable Long id, @RequestBody Patient p) {
        p.setId(id);
        return service.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    //cautare dupa nume
    @GetMapping("/search")
    public List<Patient> searchByName(@RequestParam String name) {
        return repo.findByNameContaining(name);
    }

    //cautare dupa email
    @GetMapping("/email")
    public Patient getByEmail(@RequestParam String email) {
        return repo.findByEmail(email);
    }
}