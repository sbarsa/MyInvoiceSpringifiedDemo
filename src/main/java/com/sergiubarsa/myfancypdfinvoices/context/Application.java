package com.sergiubarsa.myfancypdfinvoices.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergiubarsa.myfancypdfinvoices.service.InvoiceService;
import com.sergiubarsa.myfancypdfinvoices.service.UserService;

public class Application {

    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final UserService userService = new UserService();
    public static final InvoiceService invoiceService = new InvoiceService(userService);
}
