import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing. JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
public class test1_2811{
    public static void main(String[] args){
    	/*Add lebel*/
    	JLabel label = new JLabel();
    	label.setText("Bro, do you even code ?");
    	ImageIcon image = new ImageIcon("tree2.png");
    	//resize the image icon:
//    	Image scaledImage = image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//    	ImageIcon resized_image = new ImageIcon(scaledImage);
//    	label.setIcon(resized_image);
    	label.setIcon(image);
    	label.setHorizontalTextPosition(JLabel.CENTER);
    	label.setVerticalTextPosition(JLabel.TOP);
    	label.setForeground(Color.green);
    	label.setFont(new Font("MV Boli", Font.PLAIN,80));// set font of text
    	label.setIconTextGap(-21);//set gap of text to image
    	label.setBackground(Color.black);//set bg color
    	label.setOpaque(true);//display background
    	label.setVerticalAlignment(JLabel.CENTER);
    	label.setHorizontalAlignment(JLabel.CENTER);;
    	/*Add a Border*/
    	Border border = BorderFactory.createLineBorder(Color.green,1);
    	label.setBorder(border);
    	
    	/*Frame*/
    	JFrame frame = new JFrame();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	frame.setSize(500,500);
    	frame.setVisible(true);
    	frame.add(label);
        // JFrame = a GUI window to add components to
        //JFrame frame = new JFrame();
//        //creates a frame
//        frame.setTitle("JFrame title goes here"); //sets title of framel
//        ImageIcon image = new ImageIcon("tree2.png");
//        frame.setIconImage(image.getImage());
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize (420,420); // sets
//        frame.setVisible(true); //make frame visible    
////        frame.getContentPane().setBackground(Color.green);//change bg color using the default colors.
//        frame.getContentPane().setBackground(new Color( 27, 79, 114 ));// change bg color using custom color
//    	MyJframe frame = new MyJframe();
    }
}
