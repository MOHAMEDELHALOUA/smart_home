import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
public class MyJframe extends JFrame{
    MyJframe(){

        this.setTitle("JFrame setTitle");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(450,450);
        this.setVisible(true);

        ImageIcon image = new ImageIcon("tree2.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(27, 79, 114));
    }
}
