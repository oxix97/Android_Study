package com.example.fragmentview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BlankFragment extends Fragment {
    private Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup viewGroup = (ViewGroup)inflater.inflate
                (R.layout.fragment_blank,container,false);

        viewGroup.findViewById(R.id.button);
        button.setOnClickListener(V->{
            MainActivity activity = (MainActivity)getActivity();
            activity.onFragmentChanged(0);
        });
        return viewGroup;
    } 
}