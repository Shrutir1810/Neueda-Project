package com.example.portfolio.objects;

import java.util.Date;

public class Transaction {
    private String id; // Transaction ID
    private String user; // User email
    private String asset;
    private String company;
    private double price;
    private int quantity;
    private Date time;
    private String type; // "Buy" or "Sell"

    public Transaction() {}

    public Transaction(String id, String user, String asset, String company, double price, int quantity, Date time, String type) {
        this.id = id;
        this.user = user;
        this.asset = asset;
        this.company = company;
        this.price = price;
        this.quantity = quantity;
        this.time = time;
        this.type = type;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date timestamp) {
        this.time = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
