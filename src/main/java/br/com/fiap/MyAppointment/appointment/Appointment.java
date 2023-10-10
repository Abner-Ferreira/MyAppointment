package br.com.fiap.MyAppointment.appointment;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Appointment {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "o campo titulo é obrigatório")
    String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    String date;

    @Size(min = 10, message = "a descrição deve ter pelo menos 10 caracteres")
    String description;

    @Size(min = 10, message = "o local é obrigatório")
    String local;

    @Min(0) @Max(100)
    Integer status;
}
