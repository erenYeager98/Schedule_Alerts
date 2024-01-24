package com.app.schedule_alerts;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateCSVData extends AsyncTask<String, Void, Boolean> {
    private static final String TAG = "UpdateCSVData";
    private Context applicationContext;

    public UpdateCSVData(Context context) {
        this.applicationContext = context.getApplicationContext();
    }

    @Override
    protected Boolean doInBackground(String... urls) {
        String fileUrl = urls[0];

        try {
            // Create a URL object and open a connection
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set up input stream and read the file
            InputStream inputStream = connection.getInputStream();

            // Get app-specific external storage directory
            File storageDirectory = new File(applicationContext.getExternalFilesDir(null),"");
            if (!storageDirectory.exists()) {
                storageDirectory.mkdirs();
            }
            // Provide a filename for the output file
            File outputFile = new File(storageDirectory, "data.csv");
            FileOutputStream outputStream = new FileOutputStream(outputFile);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Close streams
            outputStream.close();
            inputStream.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        super.onPostExecute(success);
        if (success) {
            // File download and storage successful
            Log.d(TAG, "Download Successful");
            Toast.makeText(applicationContext, "Database Updated!", Toast.LENGTH_SHORT).show();

        } else {
            Log.d(TAG, "Download Failed");
            Toast.makeText(applicationContext, "Database Updation Failed!", Toast.LENGTH_SHORT).show();

            // File download and storage failed
        }
    }
}
