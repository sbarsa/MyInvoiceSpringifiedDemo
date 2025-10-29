package com.sergiubarsa.myfancypdfinvoices.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class InvoiceDto {

    @JsonProperty("user_id")
    @NotBlank
    private String userId;

    @Min(10)
    @Max(50)
    private int amount;


    public int getAmount() {
        return amount;
    }

    public String getUserId() {
        return userId;
    }
}
