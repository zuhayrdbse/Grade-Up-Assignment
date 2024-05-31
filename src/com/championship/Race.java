package com.championship;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Race {
    private Date date;
    private Map<String, Integer> results;

    public Race(Date date) {
        this.date = date;
        this.results = new HashMap<>();
    }

    public Date getDate() {
        return date;
    }

    public Map<String, Integer> getResults() {
        return results;
    }

    public void addResult(String driverName, int position) {
        results.put(driverName, position);
    }
}
