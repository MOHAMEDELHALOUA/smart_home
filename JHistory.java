import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JHistory extends JPanel {
    private fetch_data dataFetcher;    private String[] columnNames = { 
        "Time", "Temperature", "Humidity", "VoltageLevel", "gaz_value", 
        "CarPresence", "PersonPresence" 
    };
    // Create a custom TableModel that makes the table read-only

    private DefaultTableModel model = new DefaultTableModel(new Object[0][10], columnNames) {
	      @Override
	      public boolean isCellEditable(int row, int column) {
	          return false; // Disable editing for all cells
	      }
    };
//    DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//        @Override
//        public boolean isCellEditable(int row, int column) {
//            return false; // Disable editing for all cells
//        }
//    };
    JTable historyTable = new JTable(model);
    JScrollPane sp = new JScrollPane(historyTable);

    public JHistory() {
    	
        this.setLayout(new FlowLayout()); // Use FlowLayout to prevent stretching
        historyTable.setPreferredSize(new Dimension(900, 250)); // Set the table size
        historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Adjust columns
        sp.setPreferredSize(new Dimension(900, 200)); // Set the preferred size of the scroll pane
        this.add(sp); // Add the JScrollPane to the panel
        // Create an instance of fetch_data and start fetching
        dataFetcher = new fetch_data(this);
        dataFetcher.startFetching();
    }
    public void updateHistoryTable(Double temperature, Double humidity, Double voltage, Integer gas, Integer carPresence, Integer objectStatus) {
        // Get the current time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTimeString = now.format(formatter);

        // Insert the new row at the top (index 0)
        model.insertRow(0, new Object[] {dateTimeString, temperature, humidity, voltage, gas, carPresence, objectStatus });
//        historyTable.setPreferredSize(new Dimension(900, height)); // Set the table size
        // Dynamically adjust the table height
        int rowHeight = historyTable.getRowHeight();
        int rowCount = model.getRowCount();
        int newHeight = 250+ rowHeight * rowCount;

        // Update the table and scroll pane dimensions
        historyTable.setPreferredSize(new Dimension(historyTable.getPreferredSize().width, newHeight));
        revalidate(); // Refresh the layout
    }

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("JHistory Example");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        // Add the panel to the frame
        JHistory panel = new JHistory();
        frame.add(panel);
        // Set the frame visible
        frame.setVisible(true);
    }
}
