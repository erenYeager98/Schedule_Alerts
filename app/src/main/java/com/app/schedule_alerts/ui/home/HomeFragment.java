package com.app.schedule_alerts.ui.home;
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
//import com.app.schedule_alerts.databinding.FragmentHomeBinding;
//
//public class HomeFragment extends Fragment {
//
//    private FragmentHomeBinding binding;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
////        final TextView textView = binding.textHome;
////        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        return root;
//    }

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.schedule_alerts.LogDataAsyncTask;
import com.app.schedule_alerts.ProcessExecution;
import com.app.schedule_alerts.R;
import com.app.schedule_alerts.ui.slideshow.ChecklistFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    LogDataAsyncTask logDataAsyncTask = new LogDataAsyncTask();


    //    private ArrayList<String> venueList; // Assuming this is your ArrayList
    ArrayList<String> venueList ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false); // Replace with your actual layout resource

        // Initialize your venueList (you may fetch it from your data source)
//        venueList = new ArrayList<>();
////        venueList.add("Venue 1");
////        venueList.add("Holy moly");
//        venueList.add("Venue 3");
        // ... add more items as needed
        venueList = ProcessExecution.executeProcess(requireContext());
        System.out.println(venueList);
        // Dynamically generate CardViews based on venueList
        generateCardViews(rootView);

        return rootView;
    }


    private void generateCardViews(View rootView) {
        LinearLayout cardContainer = rootView.findViewById(R.id.cardContainer); // Replace with your actual container layout ID

        for (String venue : venueList) {
            // Inflate the CardView layout
            CardView cardView = (CardView) getLayoutInflater().inflate(R.layout.cardview_layout, cardContainer, false);

            // Set the title TextView text
            TextView titleTextView = cardView.findViewById(R.id.textViewTitle2); // Use your actual TextView ID
            titleTextView.setText(venue);

            // Add the CardView to the container layout
            cardContainer.addView(cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(requireContext(), "You touched "+ venue, Toast.LENGTH_SHORT).show();
                    showDifferentFragment(venue);
                }
            });
        }
    }

    private void showDifferentFragment(String venue) {
        // Create an instance of the new fragment you want to show
        ChecklistFragment anotherFragment = new ChecklistFragment(venue);
        HomeFragment thisFragment = new HomeFragment();

        // Get the FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Start a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Replace the current fragment with the new fragment
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main, anotherFragment);
        fragmentTransaction.setReorderingAllowed(true);
        // Add the transaction to the back stack (optional)
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Object binding = null;
    }
}
