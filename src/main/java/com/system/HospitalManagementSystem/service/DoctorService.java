package com.system.HospitalManagementSystem.service;

import com.system.HospitalManagementSystem.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {

    List<Doctor> getAllDoctors();
    Optional<Doctor> getDoctorById(Long id);
    Doctor saveDoctor(Doctor doctor);
    void deleteDoctor(Long id);
    Doctor updateDoctor(Long id, Doctor doctor);
}
