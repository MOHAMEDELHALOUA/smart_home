import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ESP32ClientGUI extends JFrame {

    // Declare GUI components
    private JTextField ipField;
    private JButton fetchButton;
    private JTextArea responseArea;

    // Constructor to set up the GUI
    public ESP32ClientGUI() {
        // Set up the frame
        setTitle("ESP32 Client GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel for ESP32 IP (not being used in this example, but can be for dynamic IP entry)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        ipField = new JTextField(20); // Text field for entering the IP
        inputPanel.add(ipField);
        fetchButton = new JButton("Start"); // Button to fetch data
        inputPanel.add(fetchButton);

        // Response area where the fetched data will be displayed
        responseArea = new JTextArea();
        responseArea.setEditable(false); // Make the area read-only
        JScrollPane scrollPane = new JScrollPane(responseArea);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add action listener to the button
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchMessage(); // Fetch the message when button is clicked
            }
        });
    }

    // Method to fetch data from Firebase
    private void fetchMessage() {
        try {
            // Specify the node path from Firebase that you want to fetch
            String selectedNode = "test/int"; // This can be changed to "test/float" based on what you need
            String selectedNode2 = "test/float"; // This can be changed to "test/float" based on what you need

            // Construct the URL for the Firebase Realtime Database
            URL url = new URL("https://autohouse-82c5f-default-rtdb.firebaseio.com/" + selectedNode + ".json");
            URL url2 = new URL("https://autohouse-82c5f-default-rtdb.firebaseio.com/" + selectedNode2 + ".json");

            // Open a connection to Firebase and set the HTTP request method to GET
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();

            connection.setRequestMethod("GET");
            connection2.setRequestMethod("GET");

            // Read the response from the Firebase Database
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));

            StringBuilder response = new StringBuilder();
            StringBuilder response2 = new StringBuilder();

            String line;
            String line2 = null;
            while ((line = reader.readLine()) != null && (line = reader2.readLine()) != null) {
                response.append(line).append("\n");
                response2.append(line2).append("\n");
            }
            reader.close();
            reader2.close();

            // Display the fetched data in the response area
            responseArea.setText("Node: " + selectedNode + "\nValue: " + response.toString() + "Node: " + selectedNode2 + "\nValue: " + response2.toString());

        } catch (Exception ex) {
            // Handle errors (e.g., no connection or invalid response)
            responseArea.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create and display the GUI in the event dispatch thread (best practice for GUI creation)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ESP32ClientGUI gui = new ESP32ClientGUI(); // Create the GUI instance
                gui.setVisible(true); // Make the GUI visible
            }
        });
    }
}
