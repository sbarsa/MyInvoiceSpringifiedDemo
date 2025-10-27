package com.sergiubarsa.myfancypdfinvoices.service;

import com.sergiubarsa.myfancypdfinvoices.model.Invoice;
import com.sergiubarsa.myfancypdfinvoices.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private final UserService userService;
    private final List<Invoice> invoices = new CopyOnWriteArrayList<>();


    @PostConstruct
    public void init() {
        System.out.println("testing postconstruct");

    }

    @PreDestroy
    public void shutdown() {
        System.out.println("testing predestroy");
    }

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    public Invoice create(String userId, int amount) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException();
        }

        Invoice invoice = new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);

        return invoice;
    }

    public List<Invoice> findAll() {
        return Collections.unmodifiableList(invoices);
    }
}
