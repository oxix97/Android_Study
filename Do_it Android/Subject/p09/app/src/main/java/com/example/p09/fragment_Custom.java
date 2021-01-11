package com.example.p09;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragment_Custom extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_customer__info,container,false);

        EditText name = viewGroup.findViewById(R.id.editName);
        EditText age = viewGroup.findViewById(R.id.editAge);

        Button button = viewGroup.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_name = name.getText().toString();
                String s_age = age.getText().toString();
                Toast.makeText(getContext(),s_name+"\n"+s_age, Toast.LENGTH_SHORT).show();
            }
        });
        return viewGroup;
    }
}
