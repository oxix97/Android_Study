package com.example.fragment2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    ImageSelectionCallBack callBack;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(context instanceof ImageSelectionCallBack){
            callBack = (ImageSelectionCallBack) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.
                inflate(R.layout.fragment_list,container,false);

        Button button1 = viewGroup.findViewById(R.id.button1);
        button1.setOnClickListener(v->{
            MainActivity activity = (MainActivity) getActivity();
            if(callBack != null){
                callBack.onImageSelected(0);
            }
        });

        Button button2 = viewGroup.findViewById(R.id.button2);
        button2.setOnClickListener(v->{
            if(callBack != null){
                callBack.onImageSelected(1);
            }
        });

        Button button3 = viewGroup.findViewById(R.id.button3);
        button3.setOnClickListener(v->{
            if(callBack != null){
                callBack.onImageSelected(2);
            }
        });
        return viewGroup;
    }
}