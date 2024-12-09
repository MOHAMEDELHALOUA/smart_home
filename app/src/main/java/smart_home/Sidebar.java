package smart_home;

import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {

    public Sidebar(MainWindow mainWindow) {
        setLayout(new GridLayout(4, 1, 5, 5)); // Vertical layout with space between buttons
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(150, 0)); // Fixed width

        // Create buttons for navigation
        JButton homeButton = new JButton("Home");
        JButton interface2Button = new JButton("Interface 2");
        JButton interface3Button = new JButton("Interface 3");
        JButton interface4Button = new JButton("Interface 4");

        // Add listeners to buttons
        homeButton.addActionListener(e -> mainWindow.switchInterface("Home"));
        interface2Button.addActionListener(e -> mainWindow.switchInterface("Interface2"));
        interface3Button.addActionListener(e -> mainWindow.switchInterface("Interface3"));
        interface4Button.addActionListener(e -> mainWindow.switchInterface("Interface4"));

        // Add buttons to the sidebar
        add(homeButton);
        add(interface2Button);
        add(interface3Button);
        add(interface4Button);
    }
}
