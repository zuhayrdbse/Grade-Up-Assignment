package com.championship;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Formula1ChampionshipManager implements ChampionshipManager {
    private List<Formula1Driver> drivers = new ArrayList<>();
    private List<Race> races = new ArrayList<>();

    @Override
    public void addDriver(Formula1Driver driver) {
        drivers.add(driver);
    }

    @Override
    public void removeDriver(String driverName) {
        drivers.removeIf(driver -> driver.getName().equals(driverName));
    }

    @Override
    public void changeDriverTeam(String driverName, String newTeam) {
        for (Formula1Driver driver : drivers) {
            if (driver.getName().equals(driverName)) {
                driver.setTeam(newTeam);
                break;
            }
        }
    }

    @Override
    public Formula1Driver getDriver(String driverName) {
        for (Formula1Driver driver : drivers) {
            if (driver.getName().equals(driverName)) {
                return driver;
            }
        }
        return null;
    }

    @Override
    public List<Formula1Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void addRaceResult(String driverName, int position) {
        Formula1Driver driver = getDriver(driverName);
        if (driver != null) {
            switch (position) {
                case 1:
                    driver.setFirstPositions(driver.getFirstPositions() + 1);
                    driver.setTotalPoints(driver.getTotalPoints() + 25);
                    break;
                case 2:
                    driver.setSecondPositions(driver.getSecondPositions() + 1);
                    driver.setTotalPoints(driver.getTotalPoints() + 18);
                    break;
                case 3:
                    driver.setThirdPositions(driver.getThirdPositions() + 1);
                    driver.setTotalPoints(driver.getTotalPoints() + 15);
                    break;
                case 4:
                    driver.setTotalPoints(driver.getTotalPoints() + 12);
                    break;
                case 5:
                    driver.setTotalPoints(driver.getTotalPoints() + 10);
                    break;
                case 6:
                    driver.setTotalPoints(driver.getTotalPoints() + 8);
                    break;
                case 7:
                    driver.setTotalPoints(driver.getTotalPoints() + 6);
                    break;
                case 8:
                    driver.setTotalPoints(driver.getTotalPoints() + 4);
                    break;
                case 9:
                    driver.setTotalPoints(driver.getTotalPoints() + 2);
                    break;
                case 10:
                    driver.setTotalPoints(driver.getTotalPoints() + 1);
                    break;
            }
            driver.setRacesParticipated(driver.getRacesParticipated() + 1);

            // Add race result
            Race race = new Race(new Date());
            race.addResult(driverName, position);
            races.add(race);
        }
    }

    @Override
    public List<Race> getRaces() {
        return races;
    }
}
