package com.system.HospitalManagementSystem.serviceImpl;

import com.system.HospitalManagementSystem.exception.InvalidResourceException;
import com.system.HospitalManagementSystem.exception.ResourceNotFoundException;
import com.system.HospitalManagementSystem.model.Appointment;
import com.system.HospitalManagementSystem.model.Doctor;
import com.system.HospitalManagementSystem.model.Patient;
import com.system.HospitalManagementSystem.repository.AppointmentRepository;
import com.system.HospitalManagementSystem.service.AppointmentService;
import com.system.HospitalManagementSystem.service.DoctorService;
import com.system.HospitalManagementSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        try {
            Optional<Doctor> doctor = doctorService.getDoctorById(appointment.getDoctor().getDoctorId());
            Optional<Patient> patient = patientService.getPatientById(appointment.getPatient().getPatientId());

            if (doctor.isPresent() && patient.isPresent()) {
                appointment.setDoctor(doctor.get());
                appointment.setPatient(patient.get());
            }

            return appointmentRepository.save(appointment);
        } catch (Exception e) {
            throw new InvalidResourceException("Failed to save appointment: " + e.getMessage());
        }
    }

    @Override
    public void deleteAppointment(Long id) {
        try {
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Appointment not found with id: " + id);
        }
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id)
                .map(existingAppointment -> {
                    existingAppointment.setDate(updatedAppointment.getDate());
                    return appointmentRepository.save(existingAppointment);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Appointment nor found with id: " + id)
                );
    }
}
