package com.system.HospitalManagementSystem.repository;

import com.system.HospitalManagementSystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
