package system.management.hospital.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import system.management.hospital.exception.InvalidResourceException;
import system.management.hospital.exception.ResourceNotFoundException;
import system.management.hospital.model.Doctor;
import system.management.hospital.repository.DoctorRepository;
import system.management.hospital.service.DoctorService;

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
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(Long id) throws ResourceNotFoundException {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Doctor with id : " + id + " not found.");
        }
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor updatedDoctor) throws InvalidResourceException {
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
                .orElseThrow(() ->
                        new InvalidResourceException("Doctor with id: " + id + " could not be updated.")
                );
    }
}
