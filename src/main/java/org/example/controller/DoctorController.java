package org.example.controller;

import org.example.entity.Doctor;
import org.example.service.DoctorService;
import org.example.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;
    private final DoctorRepository repo;

    public DoctorController(DoctorService service, DoctorRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping
    public List<Doctor> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Doctor create(@RequestBody Doctor d) {
        return service.save(d);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody Doctor d) {
        d.setId(id);
        return service.save(d);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    //dupa specializare
    @GetMapping("/specialization")
    public List<Doctor> getBySpecialization(@RequestParam String specialization) {
        return repo.findBySpecialization(specialization);
    }

    //dupa nume
    @GetMapping("/search")
    public List<Doctor> searchByName(@RequestParam String name) {
        return repo.findByNameContaining(name);
    }
}
