package com.system.HospitalManagementSystem.service;

import com.system.HospitalManagementSystem.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(Long id);
    Appointment saveAppointment(Appointment appointment);
    void deleteAppointment(Long id);
    Appointment updateAppointment(Long id, Appointment appointment);
}
