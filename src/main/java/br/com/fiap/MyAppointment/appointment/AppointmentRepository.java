package br.com.fiap.MyAppointment.appointment;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
}
