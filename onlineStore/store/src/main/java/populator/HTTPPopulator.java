package populator;

import categories.CategoryNames;
import http.Client;
import json.JSONComposer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;


public class HTTPPopulator implements Populator {
    private final Client client = new Client();

    public void fillStore() {
        for (CategoryNames category : CategoryNames.values()) {
            HttpURLConnection connection = client.getConnection("/category", "POST");
            connection.setDoOutput(true);

            // output
            try {
                OutputStream os = connection.getOutputStream();
                os.write(JSONComposer.categoryToJSON(category).toString().getBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
            // input
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                while (br.ready()) {
                    response.append(br.readLine().trim());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
        }
    }
}
