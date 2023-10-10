package br.com.fiap.MyAppointment.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    
    @Autowired
    AppointmentService service;

    @GetMapping
    public String index (Model model){
        model.addAttribute("appointments", service.findAll());
        return "appointment/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if (service.delete(id)){
            redirect.addFlashAttribute("success", "Consulta apagada com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Consulta não encontrada");
        }
        return "redirect:/appointment";
    }

    @GetMapping("new")
    public String form(Appointment appointment){
        return "appointment/form";
    }

    
    @PostMapping
    public String create(@Valid Appointment appointment, BindingResult result , RedirectAttributes redirect){
        if(result.hasErrors()) return "appointment/form";
        
        service.save(appointment);
        redirect.addFlashAttribute("success", "Consulta cadastrada com sucesso");
        return "redirect:/appointment";
    }
    
}
