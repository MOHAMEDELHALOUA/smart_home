package Test2_Panels.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public  class Test_JButton extends JPanel implements ActionListener{
    private int Butthon_width;
    private String buttontitle;
    private JButton button1;

    // Default Constructor
    public Test_JButton() {
        this(100, ""); // Default values: width = 100, title = ""
    }

    // Constructor with parameters
    public Test_JButton(int buttonWidth, String buttonTitle) {
        this.Butthon_width = buttonWidth;
        this.buttontitle = buttonTitle;

        // Initialize the JButton
        button1 = new JButton(buttontitle);
        button1.setPreferredSize(new Dimension(this.Butthon_width, 30));
        button1.setForeground(Color.green);
        button1.setFont(new Font("MV Boli", Font.PLAIN, 15)); // Set font of text
        /*Add Image Icon*/
//        ImageIcon image = new ImageIcon("flower.jpg");
//        button1.setIcon(image);
        
        // Set layout and add button
        this.setLayout(new BorderLayout());
        this.add(button1, BorderLayout.CENTER);
        //Add action listern
        button1.addActionListener(e -> Count_Down());
    }

    // Getter and Setter for button width
    public int getButthon_width() {
        return Butthon_width;
    }

    public void setButthon_width(int butthon_width) {
        this.Butthon_width = butthon_width;
        button1.setPreferredSize(new Dimension(this.Butthon_width, 30));
        this.revalidate(); // Refresh layout
        this.repaint();
    }

    // Getter and Setter for button title
    public String getButtontitle() {
        return buttontitle;
    }

    public void setButtontitle(String buttontitle) {
        this.buttontitle = buttontitle;
        button1.setText(this.buttontitle);
    }
    public void Count_Down() {
    	for(int i = 0; i<10;i++) {
    		try {
        		System.out.println("i = "+i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource() == button1) {
//			System.out.println("Salaaaaaam hhh");
//		}
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
}
