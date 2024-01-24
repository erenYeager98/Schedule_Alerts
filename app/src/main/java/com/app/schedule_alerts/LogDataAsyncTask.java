package com.app.schedule_alerts;

import android.os.AsyncTask;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LogDataAsyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        System.out.println("Text Appended successfully");
        String urlString = "https://3755-2401-4900-6328-dbbf-15b3-35d9-4016-7aeb.ngrok-free.app/append_file";  // Replace with your server URL
        String data = params[0];

        try {
            // Create a URL object
            URL url = new URL(urlString);

            // Open a connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Set the request method to POST
            urlConnection.setRequestMethod("POST");

            // Set content type
            urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // Enable input/output streams
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            // Create JSON object with the data
            JSONObject jsonParams = new JSONObject();
            jsonParams.put("content_to_append", data);

            // Get output stream and write the JSON object to it
            try (OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = jsonParams.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code (optional)
            int responseCode = urlConnection.getResponseCode();

            // Handle the response (if needed)
            // ...

            // Disconnect the connection
            urlConnection.disconnect();

            return "Success";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    // Add any additional code you need to handle the response on the UI thread
    @Override
    protected void onPostExecute(String result) {
        // Handle the result as needed
        // ...
    }
}
