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

import java.text.SimpleDateFormat;
import java.util.Date;

public class fragment_Custom extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_customer__info,container,false);

        EditText name = viewGroup.findViewById(R.id.editName);
        EditText age = viewGroup.findViewById(R.id.editAge);

        Button button = viewGroup.findViewById(R.id.button);
        Button birth = viewGroup.findViewById(R.id.Birth);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s_name = "이름: "+name.getText().toString();
                String s_age = "\n나이: "+age.getText().toString();
                String s_date = "\n날짜: "+birth.getText().toString();

                Toast.makeText(getContext(),s_name+s_age+s_date, Toast.LENGTH_SHORT).show();
            }
        });

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        String getTime = sdf.format(date);
        birth.setText(getTime);

        return viewGroup;
    }
}
