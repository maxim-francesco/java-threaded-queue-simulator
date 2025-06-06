package GUI;

import BusinessLogic.SelectionPolicy;
import BusinessLogic.SimulationManager;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {

    private JTextField timeLimitField;
    private JTextField maxProcessingField;
    private JTextField minProcessingField;
    private JTextField numServersField;
    private JTextField numClientsField;
    private JButton startButton;
    private JTextArea logArea;
    private JComboBox<String> strategyComboBox;

    public SimulationFrame() {
        setTitle("Simulation Settings");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen
        setLayout(new BorderLayout());

        // LEFT PANEL
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel timeLimitLabel = new JLabel("Time Limit:");
        timeLimitField = new JTextField("10");

        JLabel maxProcessingLabel = new JLabel("Max Processing Time:");
        maxProcessingField = new JTextField("5");

        JLabel minProcessingLabel = new JLabel("Min Processing Time:");
        minProcessingField = new JTextField("2");

        JLabel numServersLabel = new JLabel("Number of Servers:");
        numServersField = new JTextField("3");

        JLabel numClientsLabel = new JLabel("Number of Clients:");
        numClientsField = new JTextField("10");

        JLabel strategyLabel = new JLabel("Selection Policy:");
        strategyComboBox = new JComboBox<>(new String[] { "SHORTEST QUEUE", "SHORTEST TIME" });

        startButton = new JButton("Start");

        // Add components to left panel
        gbc.gridx = 0; gbc.gridy = 0;
        leftPanel.add(timeLimitLabel, gbc);
        gbc.gridx = 1;
        leftPanel.add(timeLimitField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        leftPanel.add(maxProcessingLabel, gbc);
        gbc.gridx = 1;
        leftPanel.add(maxProcessingField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        leftPanel.add(minProcessingLabel, gbc);
        gbc.gridx = 1;
        leftPanel.add(minProcessingField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        leftPanel.add(numServersLabel, gbc);
        gbc.gridx = 1;
        leftPanel.add(numServersField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        leftPanel.add(numClientsLabel, gbc);
        gbc.gridx = 1;
        leftPanel.add(numClientsField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        leftPanel.add(strategyLabel, gbc);
        gbc.gridx = 1;
        leftPanel.add(strategyComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        leftPanel.add(startButton, gbc);

        // RIGHT PANEL - TEXT AREA (scrollable)
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add panels to frame
        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // BUTTON ACTION
        startButton.addActionListener(e -> {
            StringBuilder log = new StringBuilder();
            log.append("Simulation Parameters:\n");
            log.append("Time Limit: ").append(timeLimitField.getText()).append("\n");
            log.append("Max Processing Time: ").append(maxProcessingField.getText()).append("\n");
            log.append("Min Processing Time: ").append(minProcessingField.getText()).append("\n");
            log.append("Number of Servers: ").append(numServersField.getText()).append("\n");
            log.append("Number of Clients: ").append(numClientsField.getText()).append("\n");
            log.append("Strategy: ").append(strategyComboBox.getSelectedItem()).append("\n\n");

            logArea.append(log.toString());

            // Redirect System.out to logArea
            System.setOut(new java.io.PrintStream(new CustomOutputStream(logArea)));

            // Parse inputs
            int timeLimit = Integer.parseInt(timeLimitField.getText());
            int maxProcTime = Integer.parseInt(maxProcessingField.getText());
            int minProcTime = Integer.parseInt(minProcessingField.getText());
            int numServers = Integer.parseInt(numServersField.getText());
            int numClients = Integer.parseInt(numClientsField.getText());
            String strategyText = (String) strategyComboBox.getSelectedItem();
            SelectionPolicy policy = strategyText.equals("SHORTEST TIME") ?
                    SelectionPolicy.SHORTEST_TIME : SelectionPolicy.SHORTEST_QUEUE;

            // Create SimulationManager and start it as a Thread
            SimulationManager manager = new SimulationManager(
                    timeLimit, maxProcTime, minProcTime, numServers, numClients, policy, logArea);

            Thread simulationThread = new Thread(manager);
            simulationThread.start();
        });

    }

}
