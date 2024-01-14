package com.app.schedule_alerts;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.app.schedule_alerts.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProcessExecution {
    private ActivityMainBinding binding;
    public static ArrayList<String> executeProcess(Context context) {
        ArrayList<String> venueList = new ArrayList<>();
        System.out.println(Calendar.getInstance().getTime().toString());
        String day = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            day = LocalDate.now().getDayOfWeek().name();
        }
        System.out.println(day);

        try {
            File csvFile = new File(String.valueOf(context.getExternalFilesDir("data.csv")));
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            StringBuilder text = new StringBuilder();
            String currentDate = null;
            String currentTime = null;
            boolean firstLine = true;
            System.out.println(currentTime + "flag");
            SimpleDateFormat sdfTime0 = new SimpleDateFormat("HH:mm", Locale.getDefault());
            currentTime = sdfTime0.format(new Date());
            System.out.println(TimeManipulation.addTenMinutes(currentTime));
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] fields = line.split(",");
                if (fields.length >= 6) {
                    SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm", Locale.getDefault());
                    currentDate = sdfDate.format(new Date());
                    currentTime = sdfTime.format(new Date());
                  //  System.out.println("It entered the first loop" + fields[1]);
                   // System.out.println(TimeManipulation.addTenMinutes(currentTime));
                    if (fields[1].equalsIgnoreCase(day)) {
                       // System.out.println("Day matches ");
                        if (fields[3].equals(TimeManipulation.addTenMinutes(currentTime))) {
                          //  System.out.println("Time also matches");
                            text.append(fields[5]);
                            text.append('\n');
                            venueList.add(fields[5]);
                         //   System.out.println("Time to notify");
                        }
                    }
                }
            }
            br.close();
            System.out.println(venueList);
            System.out.println(day + " " + currentTime + " " + "Time to open these places:" + "\n" + text.toString());

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        System.out.println(venueList.toString());

        return venueList;

    }


}
