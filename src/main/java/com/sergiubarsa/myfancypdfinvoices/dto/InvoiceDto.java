package com.sergiubarsa.myfancypdfinvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceDto {

    @JsonProperty("user_id")
    private String userId;

    private int amount;

    public int getAmount() {
        return amount;
    }

    public String getUserId() {
        return userId;
    }
}
