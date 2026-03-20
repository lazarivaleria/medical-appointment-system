package org.example.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnosis;
    private String notes;

    @OneToOne
    private Appointment appointment;

    @OneToMany(mappedBy = "medicalRecord")
    private List<Prescription> prescriptions;

    public MedicalRecord() {}

    public MedicalRecord(Long id, String diagnosis, String notes, Appointment appointment) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.notes = notes;
        this.appointment = appointment;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public List<Prescription> getPrescriptions() { return prescriptions; }
    public void setPrescriptions(List<Prescription> prescriptions) { this.prescriptions = prescriptions; }
}
