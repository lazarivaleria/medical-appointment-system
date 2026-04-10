package org.example.controller;

import org.example.entity.MedicalRecord;
import org.example.repository.MedicalRecordRepository;
import org.example.service.MedicalRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/records")
public class MedicalRecordController {

    private final MedicalRecordService service;
    private final MedicalRecordRepository repo;

    public MedicalRecordController(MedicalRecordService service, MedicalRecordRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<MedicalRecord> create(@RequestBody MedicalRecord m) {
        return new ResponseEntity<>(service.save(m), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> update(@PathVariable Long id, @RequestBody MedicalRecord m) {
        m.setId(id);
        return ResponseEntity.ok(service.save(m));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 după diagnostic
    @GetMapping("/diagnosis")
    public ResponseEntity<List<MedicalRecord>> getByDiagnosis(@RequestParam String diagnosis) {
        return ResponseEntity.ok(repo.findByDiagnosisContaining(diagnosis));
    }
}