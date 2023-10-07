package br.com.fiap.MyAppointment.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
}
