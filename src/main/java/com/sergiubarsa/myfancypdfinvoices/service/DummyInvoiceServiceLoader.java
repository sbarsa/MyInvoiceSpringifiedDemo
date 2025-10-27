package com.sergiubarsa.myfancypdfinvoices.service;


import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Apply the following JVM argument to activate:
 * -Dspring.profiles.active=dev
 */
@Service
@Profile("dev")
public class DummyInvoiceServiceLoader {

    private final InvoiceService invoiceService;

    public DummyInvoiceServiceLoader(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void setup() {
        System.out.println("Creating dev invoices...");
        invoiceService.create("someUser", 20);
        invoiceService.create("someOtherUser", 40);
    }
}
