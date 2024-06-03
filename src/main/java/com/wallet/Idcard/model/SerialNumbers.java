package com.wallet.Idcard.model;

import java.util.List;

public class SerialNumbers {

    private List<String> serialNumbers;
    private String lastUpdated;

    public List<String> getSerialNumbers() {
        return serialNumbers;
    }

    public void setSerialNumbers(List<String> serialNumbers) {
        this.serialNumbers = serialNumbers;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        return "SerialNumbers{" +
                "serialNumbers=" + serialNumbers +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
