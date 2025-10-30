package com.sergiubarsa.myfancypdfinvoices.web;

import com.sergiubarsa.myfancypdfinvoices.dto.InvoiceDto;
import com.sergiubarsa.myfancypdfinvoices.model.Invoice;
import com.sergiubarsa.myfancypdfinvoices.service.InvoiceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class InvoicesController {

    private final InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestParam("user_id") @NotBlank String userId, @RequestParam("amount") @Min(10) @Max(50) int amount) {
        return invoiceService.create(userId, amount);
    }

    @PostMapping("/invoices/{userId}/{amount}")
    public Invoice createInvoiceWithPathVariables(@PathVariable("userId") String userId, @PathVariable("amount") int amount) {
        return invoiceService.create(userId, amount);
    }

    @PostMapping("/invoicesdto")
    public Invoice createInvoiceWithRequestBody(@RequestBody @Valid InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());
    }
}
