package org.example.controller;

import org.example.entity.Appointment;
import org.example.service.AppointmentService;
import org.example.repository.AppointmentRepository;
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
    public List<Appointment> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Appointment getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Appointment create(@RequestBody Appointment a) {
        return service.save(a);
    }

    @PutMapping("/{id}")
    public Appointment update(@PathVariable Long id, @RequestBody Appointment a) {
        a.setId(id);
        return service.save(a);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    //dupa data
    @GetMapping("/date")
    public List<Appointment> getByDate(@RequestParam String date) {
        return repo.findByDate(LocalDate.parse(date));
    }

    //dupa pacient
    @GetMapping("/patient/{id}")
    public List<Appointment> getByPatient(@PathVariable Long id) {
        return repo.findByPatientId(id);
    }

    //dupa medic
    @GetMapping("/doctor/{id}")
    public List<Appointment> getByDoctor(@PathVariable Long id) {
        return repo.findByDoctorId(id);
    }
}
