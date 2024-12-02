import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ESP32ClientGUI extends JFrame {
    private JTextField ipField;
    private JButton fetchButton;
    private JTextArea responseArea;

    public ESP32ClientGUI() {
        // Set up the frame
        setTitle("ESP32 Client GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("ESP32 IP:"));
        ipField = new JTextField(20);
        inputPanel.add(ipField);
        fetchButton = new JButton("Fetch Message");
        inputPanel.add(fetchButton);

        // Response area
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(responseArea);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Add button action
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchMessage();
            }
        });
    }

    private void fetchMessage() {
        String esp32IP = ipField.getText().trim();
        if (esp32IP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the ESP32 IP address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Construct the URL
            URL url = new URL("http://" + esp32IP + "/");

            // Open connection and set method
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
            reader.close();

            // Display the response
            responseArea.setText(response.toString());
        } catch (Exception ex) {
            responseArea.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Create and display the GUI
        SwingUtilities.invokeLater(() -> {
            ESP32ClientGUI gui = new ESP32ClientGUI();
            gui.setVisible(true);
        });
    }
}
