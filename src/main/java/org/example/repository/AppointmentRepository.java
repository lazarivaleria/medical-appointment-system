package org.example.repository;

import org.example.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    //programari dupa data
    List<Appointment> findByDate(LocalDate date);

    //programari dupa pacient
    List<Appointment> findByPatientId(Long patientId);

    //programari dupa medic
    List<Appointment> findByDoctorId(Long doctorId);
}
