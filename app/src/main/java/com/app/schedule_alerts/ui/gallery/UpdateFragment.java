package com.app.schedule_alerts.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.schedule_alerts.MainActivity;
import com.app.schedule_alerts.R;
import com.app.schedule_alerts.UpdateCSVData;
import com.app.schedule_alerts.databinding.FragmentGalleryBinding;

public class UpdateFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
          View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
            Button btn = (Button) root.findViewById(R.id.updatebtn);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new UpdateCSVData(requireContext()).execute("https://erenyeager98.github.io/data.csv");
                    System.out.println("Button clicked");
                }
            });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}