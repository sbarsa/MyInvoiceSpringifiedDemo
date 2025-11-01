package com.sergiubarsa.myfancypdfinvoices.web;

import com.sergiubarsa.myfancypdfinvoices.service.InvoiceService;
import com.sergiubarsa.myfancypdfinvoices.web.forms.LoginForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class InvoicesHtmlController {

    private final InvoiceService invoiceService;

    public InvoicesHtmlController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/")
    public String homepage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            model.addAttribute("username", "Stranger");
        }
        model.addAttribute("username", username);
        model.addAttribute("currentDate", LocalDateTime.now());
        return "index.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "index.html";
        }

        //for POC purposes, we're assuming that any combinations of username == password are correct
        if (loginForm.getUsername().equals(loginForm.getPassword())) {
            session.setAttribute("username", loginForm.getUsername());
            return "redirect:/";
        }

        model.addAttribute("invalidCredentials", "true");
        return "login.html";
    }
}
