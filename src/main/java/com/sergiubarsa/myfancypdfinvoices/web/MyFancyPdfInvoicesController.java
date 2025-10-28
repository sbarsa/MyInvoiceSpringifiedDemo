package com.sergiubarsa.myfancypdfinvoices.web;

import com.sergiubarsa.myfancypdfinvoices.model.Invoice;
import com.sergiubarsa.myfancypdfinvoices.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyFancyPdfInvoicesController {

    private final InvoiceService invoiceService;

    public MyFancyPdfInvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestParam("user_id") String userId, @RequestParam("amount") int amount) {
        return invoiceService.create(userId, amount);
    }

    @PostMapping("/invoices/{userId}/{amount}")
    public Invoice createInvoiceWithPathVariables(@PathVariable("userId") String userId, @PathVariable("amount") int amount) {
        return invoiceService.create(userId, amount);
    }
}
