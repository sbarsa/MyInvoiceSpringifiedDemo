package com.sergiubarsa.myfancypdfinvoices.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Invoice {
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("pdf_url")
    private String pdfUrl;

    private int amount;

    public Invoice(String userId, int amount, String pdfUrl) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.pdfUrl = pdfUrl;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
