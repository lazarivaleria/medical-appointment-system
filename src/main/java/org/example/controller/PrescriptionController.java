package org.example.controller;

import org.example.entity.Prescription;
import org.example.repository.PrescriptionRepository;
import org.example.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Prescription>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Prescription> create(@RequestBody Prescription p) {
        return new ResponseEntity<>(service.save(p), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> update(@PathVariable Long id, @RequestBody Prescription p) {
        p.setId(id);
        return ResponseEntity.ok(service.save(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/medication")
    public ResponseEntity<List<Prescription>> getByMedication(@RequestParam String medication) {
        return ResponseEntity.ok(repo.findByMedicationContaining(medication));
    }

    @GetMapping("/record/{id}")
    public ResponseEntity<List<Prescription>> getByRecord(@PathVariable Long id) {
        return ResponseEntity.ok(repo.findByMedicalRecordId(id));
    }
}