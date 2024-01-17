package com.app.schedule_alerts;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LogDataAsyncTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        System.out.println("Unique text");
        String urlString = "https://c471-2401-4900-7b91-f834-1e5-7d0e-c973-d34f.ngrok-free.app/log";  // Replace with your server URL
        String data = params[0];

        try {
            // Encode the data to be sent in the URL
            String encodedData = URLEncoder.encode(data, "UTF-8");

            // Construct the final URL with the encoded data
            String finalUrl = urlString + "?data=" + encodedData;

            // Create a URL object
            URL url = new URL(finalUrl);

            // Open a connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            urlConnection.setRequestMethod("GET");

            // Get the response code (optional)
            int responseCode = urlConnection.getResponseCode();

            // Handle the response (if needed)
            // ...

            // Disconnect the connection
            urlConnection.disconnect();

            return "Success";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } catch (IOException e) {
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
