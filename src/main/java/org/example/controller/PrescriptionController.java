package org.example.controller;

import org.example.entity.Prescription;
import org.example.service.PrescriptionService;
import org.example.repository.PrescriptionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionService service;
    private final PrescriptionRepository repo;

    public PrescriptionController(PrescriptionService service, PrescriptionRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping
    public List<Prescription> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Prescription getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Prescription create(@RequestBody Prescription p) {
        return service.save(p);
    }

    @PutMapping("/{id}")
    public Prescription update(@PathVariable Long id, @RequestBody Prescription p) {
        p.setId(id);
        return service.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    //dupa medicament
    @GetMapping("/medication")
    public List<Prescription> getByMedication(@RequestParam String medication) {
        return repo.findByMedicationContaining(medication);
    }

    //dupa fisa medicala
    @GetMapping("/record/{id}")
    public List<Prescription> getByRecord(@PathVariable Long id) {
        return repo.findByMedicalRecordId(id);
    }
}
