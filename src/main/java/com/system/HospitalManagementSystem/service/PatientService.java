package com.system.HospitalManagementSystem.service;

import com.system.HospitalManagementSystem.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    Patient savePatient(Patient patient);
    void deletePatient(Long id);
    Patient updatePatient(Long id, Patient patient);
}
