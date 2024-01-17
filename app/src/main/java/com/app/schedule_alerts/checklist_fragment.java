//package com.app.schedule_alerts;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.FrameLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.app.schedule_alerts.R;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class checklist_fragment extends Fragment {
//
//    // Define an array of TextViews and CheckBoxes
//    private TextView[] textViews;
//    private CheckBox[] checkBoxes;
//
//    LogDataAsyncTask logDataAsyncTask = new LogDataAsyncTask();
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_checklist_fragment, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//        // Initialize array of TextViews and CheckBoxes
//        textViews = new TextView[]{
//                view.findViewById(R.id.twoSeaters),
//                view.findViewById(R.id.threeSeaters),
//                view.findViewById(R.id.projectors),
//                // Add all other TextViews here
//        };
//
//        checkBoxes = new CheckBox[]{
//                view.findViewById(R.id.twoSeatersCB),
//                view.findViewById(R.id.threeSeatersCB),
//                view.findViewById(R.id.projectorsCB),
//                // Add all other CheckBoxes here
//        };
//
//        // Find the Update Button
//        Button updateButton = view.findViewById(R.id.updateButton);
//
//        // Set a click listener for the Update Button
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Handle the logic to log the string in a text file
//                logCheckedText();
//                String data = "The data to be shared";
//                logDataAsyncTask.onPostExecute(data);
//                System.out.println("Method call worked");
//
//            }
//        });
//    }
//    private void logCheckedText() {
//        // Implement logic to write the text to a file
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("your_file.txt", true))) {
//            // Iterate through the TextViews and CheckBoxes
//            for (int i = 0; i < textViews.length; i++) {
//                String text = textViews[i].getText().toString();
//                int isChecked = checkBoxes[i].isChecked() ? 1 : 0;
//
//                // Log the text and the checkbox state in the file
//                writer.write(text + "," + isChecked);
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
