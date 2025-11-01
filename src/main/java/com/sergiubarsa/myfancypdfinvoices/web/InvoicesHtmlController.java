package com.sergiubarsa.myfancypdfinvoices.web;

import com.sergiubarsa.myfancypdfinvoices.service.InvoiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@Validated
public class InvoicesHtmlController {

    private final InvoiceService invoiceService;

    public InvoicesHtmlController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/")
    public String homepage(Model model, @RequestParam(name="username", required = false, defaultValue = "Stranger") String username) {
        model.addAttribute("username", username);
        model.addAttribute("currentDate", LocalDateTime.now());
        return "index.html";
    }
}
