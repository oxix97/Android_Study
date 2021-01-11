package com.example.myviewpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My View");

        pager = findViewById(R.id.viewPager);
        pager.setOffscreenPageLimit(3); // 3개의 프레그먼트를 받을 수 있다.

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager()); // 매니저 객체 넣음

        Fragment1 fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);

        button = findViewById(R.id.button);
        button.setOnClickListener(v->{
            pager.setCurrentItem(1); // 두번째 프래그먼트로 이동
        });
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter{  // 어뎁터가 프레그먼트 관리
        ArrayList<Fragment> items = new ArrayList<>();

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }
        
        public void addItem(Fragment item){
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return "페이지 " + position;
        }
    }
}