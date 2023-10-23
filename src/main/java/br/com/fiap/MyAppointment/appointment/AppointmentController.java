package br.com.fiap.MyAppointment.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @Autowired
    MessageSource message;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user) {
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("appointments", service.findAll());
        return "appointment/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        service.delete(id);
        redirect.addFlashAttribute("success",
                message.getMessage("appointment.delete.success", null, LocaleContextHolder.getLocale()));
        return "redirect:/appointment";
    }

    @GetMapping("new")
    public String form(Appointment appointment) {
        return "appointment/form";
    }

    @PostMapping
    public String create(@Valid Appointment appointment, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors())
            return "appointment/form";

        service.create(appointment);
        redirect.addFlashAttribute("success", "Consulta cadastrada com sucesso");
        return "redirect:/appointment";
    }

}
