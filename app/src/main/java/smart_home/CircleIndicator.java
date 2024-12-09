package smart_home;

import javax.swing.*;

import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.*;

public class CircleIndicator extends JPanel {
    private int percentage; // Current fill percentage (0-100)

    private double value; // Current value to display inside the circle

    public CircleIndicator() {
        setPreferredSize(new Dimension(200, 200)); // Set preferred size for the panel
        this.percentage = 0; // Default percentage
        this.value = 0; // Default displayed value
    }

    public int getPercentage() {
        return percentage;
    }

    public double getValue() {
        return value;
    }

    public void setPercentage(int percentage) {
        this.percentage = Math.clamp(percentage, 0, 100);// Clamp percentage to 0-100
        repaint();
    }

    public void setValue(double value) {
        this.value = value;
        repaint(); // Request repaint to update the UI
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Ensure the panel is properly rendered first
        Graphics2D g2d = (Graphics2D) g; // Cast Graphics to Graphics2D

        // Set rendering hints for smooth rendering
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create the shape
        // Shape arc = new Arc2D.Float(60, 60, 80, 80, 0, 180, Arc2D.CHORD);

        // Dimensions and position of the circle
        int circleDiameter = 150;
        int x = (getWidth() - circleDiameter) / 2;
        int y = (getHeight() - circleDiameter) / 2;

        // Create the circle
        Shape circle = new Ellipse2D.Float(x, y, circleDiameter, circleDiameter);

        // Draw the background outline
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(circle);

        // Calculate the height of the fill based on percentage
        int fillHeight = (int) (circleDiameter * (percentage / 100.0));
        int fillY = y + circleDiameter - fillHeight;

        // Clip the area to simulate bottom-to-top filling
        g2d.setClip(x, fillY, circleDiameter, fillHeight);

        // Fill the circle
        g2d.setColor(Color.GREEN);
        g2d.fill(circle);

        // Reset the clip to draw other elements
        g2d.setClip(null);

        // Draw the progress arc (outline)
        g2d.setColor(Color.CYAN); // Set color for the progress
        g2d.draw(new Arc2D.Double(x, y, circleDiameter, circleDiameter, 90, -3.6 * percentage, Arc2D.OPEN));

        // Draw the value inside the circle
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String text = String.format("%.2f", value);
        FontMetrics metrics = g2d.getFontMetrics();
        int textX = (getWidth() - metrics.stringWidth(text)) / 2;
        int textY = (getHeight() + metrics.getHeight()) / 2 - metrics.getDescent();
        g2d.setColor(Color.CYAN);
        g2d.drawString(text, textX, textY);
    }
}
