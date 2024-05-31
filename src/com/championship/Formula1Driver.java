package com.championship;

public class Formula1Driver extends Driver {
    private int firstPositions;
    private int secondPositions;
    private int thirdPositions;
    private int totalPoints;
    private int racesParticipated;

    public Formula1Driver(String name, String location, String team) {
        super(name, location, team);
    }

    public void setTeam(String team) {
        super.team = team;
    }

    public int getFirstPositions() {
        return firstPositions;
    }

    public void setFirstPositions(int firstPositions) {
        this.firstPositions = firstPositions;
    }

    public int getSecondPositions() {
        return secondPositions;
    }

    public void setSecondPositions(int secondPositions) {
        this.secondPositions = secondPositions;
    }

    public int getThirdPositions() {
        return thirdPositions;
    }

    public void setThirdPositions(int thirdPositions) {
        this.thirdPositions = thirdPositions;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getRacesParticipated() {
        return racesParticipated;
    }

    public void setRacesParticipated(int racesParticipated) {
        this.racesParticipated = racesParticipated;
    }

    @Override
    public String toString() {
        return super.toString() + ", firstPositions=" + firstPositions + ", secondPositions=" + secondPositions + ", thirdPositions=" + thirdPositions + ", totalPoints=" + totalPoints + ", racesParticipated=" + racesParticipated;
    }
}
