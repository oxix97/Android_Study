package com.example.fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ImageSelectionCallBack{
    ListFragment listFragment;
    ImageFragment imageFragment;
    int[] images = {R.drawable.dream01,R.drawable.dream02,R.drawable.dream03};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        listFragment = (ListFragment)manager.findFragmentById(R.id.list_frag);
        imageFragment = (ImageFragment)manager.findFragmentById(R.id.image_frag);
    }

    @Override
    public void onImageSelected(int position) {
        imageFragment.setImage(images[position]);
    }
}