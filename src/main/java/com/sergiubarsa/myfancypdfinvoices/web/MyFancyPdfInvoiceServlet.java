package com.sergiubarsa.myfancypdfinvoices.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiubarsa.myfancypdfinvoices.model.Invoice;
import com.sergiubarsa.myfancypdfinvoices.service.InvoiceService;
import com.sergiubarsa.myfancypdfinvoices.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class MyFancyPdfInvoiceServlet extends HttpServlet {

    private UserService userService;
    private ObjectMapper objectMapper;
    private InvoiceService invoiceService;

    @Override
    public void init() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        this.userService = ctx.getBean(UserService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
        this.invoiceService = ctx.getBean(InvoiceService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    """
                            <html>
                            <body>
                            <h1>Hello World</h1>
                            <p>This is my very first, embedded Tomcat, HTML Page!</p>
                            </body>
                            </html>
                            
                            """
            );
        } else if (request.getRequestURI().equalsIgnoreCase("/invoices")) {
            response.setContentType("application/json; charset=UTF-8");

            List<Invoice> allInvoices = invoiceService.findAll();

            response.getWriter().print(objectMapper.writeValueAsString(allInvoices));

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/invoices")) {

            String userId = request.getParameter("user_id");
            int amount = Integer.parseInt(request.getParameter("amount"));

            Invoice invoice = invoiceService.create(userId, amount);

            response.setContentType("application/json; charset=UTF-8");

            String json = new ObjectMapper().writeValueAsString(invoice);
            response.getWriter().print(json);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        }
    }
}