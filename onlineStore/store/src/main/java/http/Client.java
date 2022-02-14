package http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Client {
    public static final String HOST = "localhost";
    public static final int PORT = 8080;
    public static final String PROTOCOL = "http";
    public static final String USERNAME = "admin";
    public static final String PASSWORD = "password";

    public HttpURLConnection getConnection(String path, String method) {
        HttpURLConnection connection = null;
        try {
            URL address = new URL(PROTOCOL, HOST, PORT, path);
            connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod(method);
            Base64.Encoder encoder = Base64.getEncoder();
            String auth = USERNAME + ":" + PASSWORD;
            connection.setRequestProperty("Authorization", "Basic " + encoder.encodeToString(auth.getBytes(StandardCharsets.UTF_8)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
