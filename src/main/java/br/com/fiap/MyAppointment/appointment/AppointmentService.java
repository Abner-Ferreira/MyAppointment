package br.com.fiap.MyAppointment.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository repository;

    public List<Appointment> findAll() {
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var appointment = repository.findById(id);

        if (appointment.isEmpty()) return false;

        repository.deleteById(id);
        return true;
    }

    public void save(Appointment appointment) {
        repository.save(appointment);
    }
    
}