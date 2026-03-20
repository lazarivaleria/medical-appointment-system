package org.example.entity;

import jakarta.persistence.*;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String medication;
    private String dosage;

    @ManyToOne
    private MedicalRecord medicalRecord;

    public Prescription() {}

    public Prescription(Long id, String medication, String dosage, MedicalRecord medicalRecord) {
        this.id = id;
        this.medication = medication;
        this.dosage = dosage;
        this.medicalRecord = medicalRecord;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public MedicalRecord getMedicalRecord() { return medicalRecord; }
    public void setMedicalRecord(MedicalRecord medicalRecord) { this.medicalRecord = medicalRecord; }
}