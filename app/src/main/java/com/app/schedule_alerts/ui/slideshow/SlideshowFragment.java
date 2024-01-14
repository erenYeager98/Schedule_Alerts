//package com.app.schedule_alerts.ui.slideshow;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModelProvider;
//
//import com.app.schedule_alerts.databinding.FragmentSlideshowBinding;
//
//public class SlideshowFragment extends Fragment {
//
//    private FragmentSlideshowBinding binding;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        SlideshowViewModel slideshowViewModel =
//                new ViewModelProvider(this).get(SlideshowViewModel.class);
//
//        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}
package com.app.schedule_alerts.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.app.schedule_alerts.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class SlideshowFragment extends Fragment {
    String currentTime = null;
    SimpleDateFormat sdfTime0 = new SimpleDateFormat("HH:mm", Locale.getDefault());


    // Define an array of TextViews and CheckBoxes
    private TextView[] textViews;
    private CheckBox[] checkBoxes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checklist_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentTime = sdfTime0.format(new Date());

        // Initialize array of TextViews and CheckBoxes
        textViews = new TextView[]{
                view.findViewById(R.id.twoSeaters),
                view.findViewById(R.id.threeSeaters),
                view.findViewById(R.id.projectors),
                view.findViewById(R.id.board),
                view.findViewById(R.id.key_board),
                view.findViewById(R.id.CPU),
                view.findViewById(R.id.mouse),
                view.findViewById(R.id.HDMI),
                view.findViewById(R.id.MIC),
                view.findViewById(R.id.WIFI),
                view.findViewById(R.id.amplifire),
                view.findViewById(R.id.ACorNonAC),
                view.findViewById(R.id.fan),
                view.findViewById(R.id.light),
                view.findViewById(R.id.curtain),
                view.findViewById(R.id.status),
                view.findViewById(R.id.camera),
                view.findViewById(R.id.chair)
        };

        checkBoxes = new CheckBox[]{
                view.findViewById(R.id.twoSeatersCB),
                view.findViewById(R.id.threeSeatersCB),
                view.findViewById(R.id.projectorsCB),
                view.findViewById(R.id.boardCB),
                view.findViewById(R.id.key_boardCB),
                view.findViewById(R.id.CPUCB),
                view.findViewById(R.id.mouseCB),
                view.findViewById(R.id.HDMICB),
                view.findViewById(R.id.MICCB),
                view.findViewById(R.id.WIFICB),
                view.findViewById(R.id.amplifireCB),
                view.findViewById(R.id.ACorNonACCB),
                view.findViewById(R.id.fanCB),
                view.findViewById(R.id.lightCB),
                view.findViewById(R.id.curtainCB),
                view.findViewById(R.id.statusCB),
                view.findViewById(R.id.cameraCB),
                view.findViewById(R.id.chairCB)
        };

        // Find the Update Button
        Button updateButton = view.findViewById(R.id.updateButton);

        // Set a click listener for the Update Button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the logic to log the string in a text file
                logCheckedText();
            }
        });
    }

    // ...

    private void logCheckedText() {
        // Get the external files directory for your app
        File externalFilesDir = requireContext().getExternalFilesDir(null);

        if (externalFilesDir != null) {
            // Create a file in the external files directory
            File storageDirectory = new File(requireContext().getExternalFilesDir(null), "stocks.txt");
            if (!storageDirectory.exists()) {
                storageDirectory.mkdirs();
            }
            File logFile = new File(requireContext().getExternalFilesDir(null), "stocks.txt");

            // Implement logic to write the text to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                // Iterate through the TextViews and CheckBoxes

                for (int i = 0; i < textViews.length; i++) {
                    String text = textViews[i].getText().toString();
                    int isChecked = checkBoxes[i].isChecked() ? 1 : 0;

                    // Log the text and the checkbox state in the file
                    writer.write(text + "," + isChecked);
                    writer.newLine();
                }
                writer.write(String.valueOf(currentTime));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
