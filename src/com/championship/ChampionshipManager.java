package com.championship;

import java.util.List;

public interface ChampionshipManager {
    void addDriver(Formula1Driver driver);
    void removeDriver(String driverName);
    void changeDriverTeam(String driverName, String newTeam);
    Formula1Driver getDriver(String driverName);
    List<Formula1Driver> getDrivers();
    void addRaceResult(String driverName, int position);
    List<Race> getRaces();
}
