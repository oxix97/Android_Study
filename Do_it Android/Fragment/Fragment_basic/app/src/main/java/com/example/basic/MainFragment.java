package com.example.basic;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {//액티비티와 프레그먼트가 연결
        //해당 파일의 것을 container에 넣음
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_main,
                container, false);
        Button button = viewGroup.findViewById(R.id.button);
        button.setOnClickListener(v->{
            MainActivity mainActivity = (MainActivity)getActivity();
            mainActivity.onFragmentChanged(1);
        });
        return viewGroup;
    }
}