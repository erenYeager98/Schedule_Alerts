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

public class checkList extends Fragment {

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

        // Initialize array of TextViews and CheckBoxes
        textViews = new TextView[]{
                view.findViewById(R.id.twoSeaters),
                view.findViewById(R.id.threeSeaters),
                view.findViewById(R.id.projectors),
                // Add all other TextViews here
        };

        checkBoxes = new CheckBox[]{
                view.findViewById(R.id.twoSeatersCB),
                view.findViewById(R.id.threeSeatersCB),
                view.findViewById(R.id.projectorsCB),
                // Add all other CheckBoxes here
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
            File logFile = new File(externalFilesDir, "stocks.txt");

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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
