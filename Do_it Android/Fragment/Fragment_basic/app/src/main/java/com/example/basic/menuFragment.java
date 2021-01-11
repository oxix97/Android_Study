package com.example.basic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class menuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_menu,
                container, false);
        Button button = viewGroup.findViewById(R.id.button);
        button.setOnClickListener(v->{
            MainActivity mainActivity = (MainActivity)getActivity();
            mainActivity.onFragmentChanged(0);
        });
        return viewGroup;
    }
}