package com.sergiubarsa.myfancypdfinvoices.service;

import com.sergiubarsa.myfancypdfinvoices.model.Invoice;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceService {

    private final UserService userService;
    private final String cdnUrl;
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        System.out.println("testing postconstruct");

    }

    @PreDestroy
    public void shutdown() {
        System.out.println("testing predestroy");
    }

    public InvoiceService(UserService userService, JdbcTemplate jdbcTemplate, @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.jdbcTemplate = jdbcTemplate;
        this.cdnUrl = cdnUrl;
    }

    public Invoice create(String userId, int amount) {
        throw new UnsupportedOperationException("not implemented");
    }

    public List<Invoice> findAll() {
        String query = "select id, user_id, pdf_url, amount from invoices";
        return jdbcTemplate.query(query, (resultSet, _) -> {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getObject("id").toString());
            invoice.setPdfUrl(resultSet.getString("pdf_url"));
            invoice.setUserId(resultSet.getString("user_id"));
            invoice.setAmount(resultSet.getInt("amount"));
            return invoice;
        });
    }
}
