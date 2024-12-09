package test;

import smart_home.*;

import javax.swing.JFrame;
import javax.swing.Timer;

public class CircleIndicatorTest extends JFrame {
    private CircleIndicator temperatureIndicator;

    public CircleIndicatorTest() {
        super("Smart Home");
        this.setSize(800, 600);
        temperatureIndicator = new CircleIndicator();
        add(temperatureIndicator);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(100, null); // Create a timer with 100ms interval
        timer.addActionListener(e -> {
            int currentPercentage = temperatureIndicator.getPercentage();

            if (currentPercentage < 100) {
                temperatureIndicator.setPercentage(currentPercentage + 1); // Increment percentage
                temperatureIndicator.setValue(currentPercentage + 1);
            } else {
                temperatureIndicator.setPercentage(0);
                temperatureIndicator.setValue(0);
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        CircleIndicatorTest w = new CircleIndicatorTest();
    }
}
