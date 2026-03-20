package org.example.controller;

import org.example.entity.MedicalRecord;
import org.example.service.MedicalRecordService;
import org.example.repository.MedicalRecordRepository;
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
    public List<MedicalRecord> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MedicalRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public MedicalRecord create(@RequestBody MedicalRecord m) {
        return service.save(m);
    }

    @PutMapping("/{id}")
    public MedicalRecord update(@PathVariable Long id, @RequestBody MedicalRecord m) {
        m.setId(id);
        return service.save(m);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    //dupa diagnostic
    @GetMapping("/diagnosis")
    public List<MedicalRecord> getByDiagnosis(@RequestParam String diagnosis) {
        return repo.findByDiagnosisContaining(diagnosis);
    }
}
