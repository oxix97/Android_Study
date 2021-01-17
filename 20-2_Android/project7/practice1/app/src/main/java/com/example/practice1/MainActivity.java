package com.example.practice1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Dog");

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.edit1);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.image,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.dog1:
                imageView.setImageResource(R.drawable.dog1); return true;
            case R.id.dog2:
                imageView.setImageResource(R.drawable.dog2); return true;
            case R.id.dog3:
                imageView.setImageResource(R.drawable.person); return true;
            case R.id.rotate:
                imageView.setRotation(Integer.parseInt(editText.getText().toString()));
                return true;
        }
        return false;
    }
}