package smart_home;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {

    public HomePanel() {
        setBackground(Color.WHITE);

        // Create panels
        CircleIndicator panel1 = new CircleIndicator();
        panel1.setPercentage(10);

        CircleIndicator panel2 = new CircleIndicator();
        panel2.setPercentage(20);

        CircleIndicator panel3 = new CircleIndicator();
        panel3.setPercentage(30);

        CircleIndicator panel4 = new CircleIndicator();
        panel4.setPercentage(40);

        CircleIndicator panel5 = new CircleIndicator();
        panel5.setPercentage(50);

        // GroupLayout setup
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(panel1)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panel2)
                                .addComponent(panel3))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panel4)
                                .addComponent(panel5)));

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(panel1)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(panel2)
                                .addComponent(panel3))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(panel4)
                                .addComponent(panel5)));
    }
}
