package org.example.service;

import org.example.entity.Appointment;
import org.example.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository repo;

    public AppointmentService(AppointmentRepository repo) {
        this.repo = repo;
    }

    public List<Appointment> getAll() {
        return repo.findAll();
    }

    public Appointment getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Programarea nu exista!"));
    }

    public Appointment save(Appointment a) {

        if (a.getDate() == null)
            throw new RuntimeException("Data este obligatorie!");

        if (a.getDate().isBefore(LocalDate.now()))
            throw new RuntimeException("Data nu poate fi in trecut!");

        if (a.getPatient() == null)
            throw new RuntimeException("Pacientul este obligatoriu!");

        if (a.getDoctor() == null)
            throw new RuntimeException("Doctorul este obligatoriu!");

        if (a.getStatus() == null || a.getStatus().isEmpty())
            throw new RuntimeException("Statusul este obligatoriu!");

        return repo.save(a);
    }

    public void delete(Long id) {
        if (!repo.existsById(id))
            throw new RuntimeException("Programarea nu exista!");
        repo.deleteById(id);
    }

    // custom
    public List<Appointment> getByDate(LocalDate date) {
        return repo.findByDate(date);
    }

    public List<Appointment> getByPatient(Long patientId) {
        return repo.findByPatientId(patientId);
    }

    public List<Appointment> getByDoctor(Long doctorId) {
        return repo.findByDoctorId(doctorId);
    }
}
