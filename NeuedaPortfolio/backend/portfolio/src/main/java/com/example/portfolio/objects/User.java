package com.example.portfolio.objects;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class User {

    private String name; // Camel case
    private Map<String, Map<String, Double>> assets;
    private Map<String, Integer> companiesInvested;
    private Map<String, Integer> dividends;
    private int portfolioValue;
    private int totalInvestedAmount;
    private List<String> transactionIds; // List of transaction IDs


    public User() {
        super();
    }

    public User(String name, Map<String, Map<String, Double>> assets, Map<String, Integer> companiesInvested, Map<String, Integer> dividends, int portfolioValue, int totalInvestedAmount,List<String> transactionIds) {
        this.name = name;
        this.assets = assets;
        this.companiesInvested = companiesInvested;
        this.dividends = dividends;
        this.portfolioValue = portfolioValue;
        this.totalInvestedAmount = totalInvestedAmount;
        this.transactionIds = transactionIds;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Map<String, Double>> getAssets() {
        return assets;
    }

    public void setAssets(Map<String, Map<String, Double>> assets) {
        this.assets = assets;
    }

    public Map<String, Integer> getCompaniesInvested() {
        return companiesInvested;
    }

    public void setCompaniesInvested(Map<String, Integer> companiesInvested) {
        this.companiesInvested = companiesInvested;
    }

    public Map<String, Integer> getDividends() {
        return dividends;
    }

    public void setDividends(Map<String, Integer> dividends) {
        this.dividends = dividends;
    }

    public int getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(int portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    public int getTotalInvestedAmount() {
        return totalInvestedAmount;
    }

    public void setTotalInvestedAmount(int totalInvestedAmount) {
        this.totalInvestedAmount = totalInvestedAmount;
    }

    public List<String> getTransactionIds() {
        return transactionIds;
    }

    public void setTransactionIds(List<String> transactionIds) {
        this.transactionIds = transactionIds;
    }

}
