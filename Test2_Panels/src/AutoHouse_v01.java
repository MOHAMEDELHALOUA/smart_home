package Test2_Panels.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AutoHouse_v01 extends JFrame implements ActionListener {
    /* Button to control the side_panel */
    JButton side_button = new JButton();
    // Create the side panel:
    JPanel side_panel = new JPanel();

    public AutoHouse_v01() {
        super();

        // Customize the side panel
        side_panel.setBackground(new Color(27, 79, 114));
        side_panel.setPreferredSize(new Dimension(200, 0));
        side_panel.setLayout(new BoxLayout(side_panel, BoxLayout.Y_AXIS)); // Set layout to vertical

        /* Customize the button */
        side_button.setMaximumSize(new Dimension(60, 50)); // Set max size for better alignment
        side_button.setForeground(Color.GREEN);
        ImageIcon setting_icon = new ImageIcon("tree.jpg");
        Image scaledImage = setting_icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Resize to 50x50
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        side_button.setIcon(resizedIcon);
        side_button.addActionListener(this);

        /* Add label */
        JLabel label1 = new JLabel("Login and Settings");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("MV Boli", Font.PLAIN, 15));
        label1.setAlignmentX(CENTER_ALIGNMENT); // Center-align the label horizontally

        /* Add components to the side_panel */
        side_panel.add(side_button); // Add the button
        side_panel.add(label1); // Add the label directly below the button

        // Set the frame:
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(500, 500);
        this.add(side_panel, BorderLayout.WEST);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == side_button) {
            side_panel.setPreferredSize(new Dimension(20, 0)); // Resize the side panel
            this.revalidate(); // Update the layout
        }
    }
}
