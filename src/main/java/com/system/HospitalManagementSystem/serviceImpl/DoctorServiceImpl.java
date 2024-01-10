package com.system.HospitalManagementSystem.serviceImpl;

import com.system.HospitalManagementSystem.exception.InvalidResourceException;
import com.system.HospitalManagementSystem.exception.ResourceNotFoundException;
import com.system.HospitalManagementSystem.model.Doctor;
import com.system.HospitalManagementSystem.repository.DoctorRepository;
import com.system.HospitalManagementSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        try {
            return doctorRepository.save(doctor);
        } catch (Exception e) {
            throw new InvalidResourceException("Failed to save doctor: " + e.getMessage());
        }
    }

    @Override
    public void deleteDoctor(Long id) {
        try {
            doctorRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Doctor not found with id: " + id);
        }
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
        return doctorRepository.findById(id)
                .map(existingDoctor -> {
                    existingDoctor.setFirstName(updatedDoctor.getFirstName());
                    existingDoctor.setLastName(updatedDoctor.getLastName());
                    existingDoctor.setSpecialization(updatedDoctor.getSpecialization());
                    existingDoctor.setContactNumber(updatedDoctor.getContactNumber());
                    existingDoctor.setAddress(updatedDoctor.getAddress());
                    existingDoctor.setFees(updatedDoctor.getFees());
                    return doctorRepository.save(existingDoctor);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }
}
