package com.app.schedule_alerts;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeManipulation {

    public static String addTenMinutes(String inputTime) {
        // Define a formatter for the input time
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("HH:mm");
        }

        // Parse the input time string to LocalTime
        LocalTime time = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            time = LocalTime.parse(inputTime, formatter);
        }

        // Add 10 minutes to the time
        LocalTime newTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            newTime = time.plusMinutes(10);
        }

        // Format the result as a string
        String result = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            result = newTime.format(formatter);
        }

        return result;
    }
}
