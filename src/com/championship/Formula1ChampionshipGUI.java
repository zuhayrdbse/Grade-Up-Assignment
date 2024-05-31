package com.championship;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Formula1ChampionshipGUI {
    private Formula1ChampionshipManager manager;
    private JFrame frame;
    private JTable driverTable;
    private DefaultTableModel tableModel;
    private DefaultTableModel raceTableModel;
    private JTable raceTable;

    public Formula1ChampionshipGUI(Formula1ChampionshipManager manager) {
        this.manager = manager;
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Formula 1 Championship Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Create table to display driver statistics
        String[] columnNames = {"Driver", "Team", "First Positions", "Second Positions", "Third Positions", "Total Points", "Races Participated"};
        tableModel = new DefaultTableModel(columnNames, 0);
        driverTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(driverTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create table to display race details
        String[] raceColumnNames = {"Date", "Driver", "Position"};
        raceTableModel = new DefaultTableModel(raceColumnNames, 0);
        raceTable = new JTable(raceTableModel);
        JScrollPane raceScrollPane = new JScrollPane(raceTable);
        frame.add(raceScrollPane, BorderLayout.EAST);

        // Create buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton btnDisplayDescending = new JButton("Display Descending Order of Points");
        btnDisplayDescending.addActionListener(this::displayDescending);
        buttonPanel.add(btnDisplayDescending);

        JButton btnDisplayAscending = new JButton("Display Ascending Order of Points");
        btnDisplayAscending.addActionListener(this::displayAscending);
        buttonPanel.add(btnDisplayAscending);

        JButton btnDisplayFirstPositions = new JButton("Display Descending Order of First Positions");
        btnDisplayFirstPositions.addActionListener(this::displayFirstPositions);
        buttonPanel.add(btnDisplayFirstPositions);

        JButton btnGenerateRandomRace = new JButton("Generate Random Race");
        btnGenerateRandomRace.addActionListener(this::generateRandomRace);
        buttonPanel.add(btnGenerateRandomRace);

        JButton btnGenerateProbabilisticRace = new JButton("Generate Probabilistic Race");
        btnGenerateProbabilisticRace.addActionListener(this::generateProbabilisticRace);
        buttonPanel.add(btnGenerateProbabilisticRace);

        JButton btnDisplayRaces = new JButton("Display All Races by Date");
        btnDisplayRaces.addActionListener(this::displayRacesByDate);
        buttonPanel.add(btnDisplayRaces);

        JButton btnSearchRaces = new JButton("Search Races by Driver");
        btnSearchRaces.addActionListener(this::searchRacesByDriver);
        buttonPanel.add(btnSearchRaces);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }

    private void displayDescending(ActionEvent event) {
        List<Formula1Driver> drivers = manager.getDrivers();
        drivers.sort(Comparator.comparingInt(Formula1Driver::getTotalPoints).reversed().thenComparingInt(Formula1Driver::getFirstPositions).reversed());
        updateDriverTable(drivers);
    }

    private void displayAscending(ActionEvent event) {
        List<Formula1Driver> drivers = manager.getDrivers();
        drivers.sort(Comparator.comparingInt(Formula1Driver::getTotalPoints).thenComparingInt(Formula1Driver::getFirstPositions));
        updateDriverTable(drivers);
    }

    private void displayFirstPositions(ActionEvent event) {
        List<Formula1Driver> drivers = manager.getDrivers();
        drivers.sort(Comparator.comparingInt(Formula1Driver::getFirstPositions).reversed().thenComparingInt(Formula1Driver::getTotalPoints).reversed());
        updateDriverTable(drivers);
    }

    private void generateRandomRace(ActionEvent event) {
        List<Formula1Driver> drivers = manager.getDrivers();
        Collections.shuffle(drivers);
        for (int i = 0; i < drivers.size(); i++) {
            manager.addRaceResult(drivers.get(i).getName(), i + 1);
        }
        updateDriverTable(drivers);
    }

    private void generateProbabilisticRace(ActionEvent event) {
        List<Formula1Driver> drivers = manager.getDrivers();
        Collections.shuffle(drivers);

        Random random = new Random();
        for (int i = 0; i < drivers.size(); i++) {
            int position = 0;
            if (i == 0 && random.nextInt(100) < 40) {
                position = 1;
            } else if (i == 1 && random.nextInt(100) < 30) {
                position = 1;
            } else if ((i == 2 || i == 3) && random.nextInt(100) < 10) {
                position = 1;
            } else if (i >= 4 && i <= 8 && random.nextInt(100) < 2) {
                position = 1;
            } else {
                position = random.nextInt(drivers.size() - 1) + 2;
            }
            manager.addRaceResult(drivers.get(i).getName(), position);
        }
        updateDriverTable(drivers);
    }

    private void displayRacesByDate(ActionEvent event) {
        List<Race> races = manager.getRaces();
        races.sort(Comparator.comparing(Race::getDate));
        updateRaceTable(races);
    }

    private void searchRacesByDriver(ActionEvent event) {
        String driverName = JOptionPane.showInputDialog(frame, "Enter Driver Name:");
        List<Race> races = manager.getRaces().stream()
                .filter(race -> race.getResults().containsKey(driverName))
                .collect(Collectors.toList());
        updateRaceTable(races);
    }

    private void updateDriverTable(List<Formula1Driver> drivers) {
        tableModel.setRowCount(0);
        for (Formula1Driver driver : drivers) {
            tableModel.addRow(new Object[]{
                    driver.getName(),
                    driver.getTeam(),
                    driver.getFirstPositions(),
                    driver.getSecondPositions(),
                    driver.getThirdPositions(),
                    driver.getTotalPoints(),
                    driver.getRacesParticipated()
            });
        }
    }

    private void updateRaceTable(List<Race> races) {
        raceTableModel.setRowCount(0);
        for (Race race : races) {
            for (Map.Entry<String, Integer> entry : race.getResults().entrySet()) {
                raceTableModel.addRow(new Object[]{
                        race.getDate(),
                        entry.getKey(),
                        entry.getValue()
                });
            }
        }
    }

    public static void main(String[] args) {
        Formula1ChampionshipManager manager = new Formula1ChampionshipManager();
        manager.addDriver(new Formula1Driver("Lewis Hamilton", "UK", "Mercedes"));
        manager.addDriver(new Formula1Driver("Max Verstappen", "Netherlands", "Red Bull"));
        manager.addDriver(new Formula1Driver("Sebastian Vettel", "Germany", "Aston Martin"));

        SwingUtilities.invokeLater(() -> new Formula1ChampionshipGUI(manager));
    }
}
