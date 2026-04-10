package org.example.controller;

import org.example.entity.Appointment;
import org.example.repository.AppointmentRepository;
import org.example.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;
    private final AppointmentRepository repo;

    public AppointmentController(AppointmentService service, AppointmentRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment a) {
        return new ResponseEntity<>(service.save(a), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id, @RequestBody Appointment a) {
        a.setId(id);
        return ResponseEntity.ok(service.save(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/date")
    public ResponseEntity<List<Appointment>> getByDate(@RequestParam String date) {
        return ResponseEntity.ok(repo.findByDate(LocalDate.parse(date)));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<Appointment>> getByPatient(@PathVariable Long id) {
        return ResponseEntity.ok(repo.findByPatientId(id));
    }

    @GetMapping("/doctor/{id}")
    public ResponseEntity<List<Appointment>> getByDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(repo.findByDoctorId(id));
    }
}