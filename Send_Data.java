import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import org.json.JSONObject; // Add this library for JSON parsing (requires org.json.jar)

public class Send_Data {
    public static void main(String[] args) throws IOException {
        // Start the server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/receive-data", new SensorDataHandler()); // Endpoint for receiving data
        server.setExecutor(null); // Use default executor
        server.start();
        System.out.println("Server started on port 8000");
    }

    static class SensorDataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Check if the request is a POST method
            if ("POST".equals(exchange.getRequestMethod())) {
                // Read the incoming JSON payload
                String jsonPayload = new String(exchange.getRequestBody().readAllBytes());
                System.out.println("Received Data: " + jsonPayload);

                // Parse the JSON data
                JSONObject jsonData = new JSONObject(jsonPayload);
                double loadVoltage = jsonData.getDouble("loadVoltage");
//                double current_mA = jsonData.getDouble("current_mA");
//                double power_mW = jsonData.getDouble("power_mW");

                // Display the received values (simulate GUI integration)
                System.out.println("Load Voltage: " + loadVoltage + " V");
//                System.out.println("Current: " + current_mA + " mA");
//                System.out.println("Power: " + power_mW + " mW");

                // Respond to the client
                String response = "Data Received Successfully";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                // Handle unsupported HTTP methods
                exchange.sendResponseHeaders(405, -1); // 405: Method Not Allowed
            }
        }
    }
}
