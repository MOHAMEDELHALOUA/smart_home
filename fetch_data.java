import org.json.JSONObject;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetch_data {
//	private int height = 300;
    private JHistory historyPanel;
    private int raws = 0;
    public fetch_data(JHistory historyPanel) {
        this.historyPanel = historyPanel;
    }

    public void startFetching() {
        // Use a background thread for fetching
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    fetchMessage();
                    Thread.sleep(4000); // Fetch data every second
                }
            }
        }.execute();
    }

    private void fetchMessage() {
        try {
            // Simulate fetching data (replace this with actual logic)
            String selectedNode = "test";
            URL url = new URL("https://autohouse-82c5f-default-rtdb.firebaseio.com/" + selectedNode + ".json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            Double temperature = jsonResponse.optDouble("temperature", 0.0);
            Double humidity = jsonResponse.optDouble("humidity", 0.0);
            Double voltage = jsonResponse.optDouble("voltage_level", 0.0);
            Integer gas = jsonResponse.optInt("gaz_value", 0);
            Integer carPresence = jsonResponse.optInt("car_presence", 0);
            Integer objectStatus = jsonResponse.optInt("object", 0);

            // Update the JHistory table
            SwingUtilities.invokeLater(() -> {
//            	height += 10;
                historyPanel.updateHistoryTable(temperature, humidity, voltage, gas, carPresence, objectStatus);
            });

        } catch (Exception e) {
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }
}

