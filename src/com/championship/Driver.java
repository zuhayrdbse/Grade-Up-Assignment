package com.championship;

public abstract class Driver {
    private String name;
    private String location;
    protected String team;

    public Driver(String name, String location, String team) {
        this.name = name;
        this.location = location;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "Driver{name='" + name + "', location='" + location + "', team='" + team + "'}";
    }
}
