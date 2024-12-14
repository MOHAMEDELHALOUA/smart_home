import javax.swing.*;

public class fetch_data extends JFrame {

    // Declare GUI components
    private JButton fetchButton;
    private JTextArea responseArea;

    // Lists to store data
    private List<Double> temperatureList = new ArrayList<>();
    private List<Double> humidityList = new ArrayList<>();
    private List<Integer> gasValueList = new ArrayList<>();
    private List<Integer> objectStatusList = new ArrayList<>();

    // Constructor to set up the GUI
    public fetch_data() {
        // Set up the frame
        setTitle("ESP32 Client GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel for ESP32 IP (not being used in this example, but can be for dynamic IP entry)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
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
//        fetchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	while(true) {
//                    fetchMessage(); // Fetch the message when button is clicked
//
//            	}
//            }
//        });
        fetchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        // Run the fetch loop in a background thread
                        while (true) {
                            fetchMessage(); // Fetch the message
                            Thread.sleep(1000); // Wait for 5 seconds
                        }
                    }
                }.execute(); // Start the background task
            }
        }); 
    }

    // Method to fetch data from Firebase
    private void fetchMessage() {
        try {
            // Firebase URL
            String selectedNode = "test";
            URL url = new URL("https://autohouse-82c5f-default-rtdb.firebaseio.com/" + selectedNode + ".json");

            // Open HTTP connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            for (String key : jsonResponse.keySet()) {
                Object value = jsonResponse.get(key);

                switch (key.toLowerCase()) { // Case-insensitive comparison
                    case "temperature":
                        temperatureList.add(((Number) value).doubleValue());
                        break;
                    case "humidity":
                        humidityList.add(((Number) value).doubleValue());
                        break;
                    case "gaz_value":
                        gasValueList.add(((Number) value).intValue());
                        break;
                    case "object":
                        objectStatusList.add(((Number) value).intValue());
                        break;
                    default:
                        System.err.println("Unexpected key: " + key);
                        break;
                }
            }

            // Display the latest data in the response area
            String result = "temperature: " + getLasttemperature() + "\n" +
                            "Humidity: " + getLastHumidity() + "\n" +
                            "Gas Value: " + getLastGasValue() + "\n" +
                            "Object: " + getLastObjectStatus() + "\n";
            responseArea.setText(result);

        } catch (Exception ex) {
            responseArea.setText("Error fetching data: " + ex.getMessage());
        }
    }

    public Double getLasttemperature() {
        return temperatureList.isEmpty() ? null : temperatureList.get(temperatureList.size() - 1);
    }

    public Double getLastHumidity() {
        return humidityList.isEmpty() ? null : humidityList.get(humidityList.size() - 1);
    }

    public Integer getLastGasValue() {
        return gasValueList.isEmpty() ? null : gasValueList.get(gasValueList.size() - 1);
    }

    public Integer getLastObjectStatus() {
        return objectStatusList.isEmpty() ? null : objectStatusList.get(objectStatusList.size() - 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	fetch_data gui = new fetch_data();
            gui.setVisible(true);
        });
    }
}


