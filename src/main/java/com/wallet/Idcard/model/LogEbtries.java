package com.wallet.Idcard.model;

import java.util.List;

public class LogEbtries {

    private List<String> logs;

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return "LogEbtries{" +
                "logs=" + logs +
                '}';
    }
}
