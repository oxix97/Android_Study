package com.example.action_bar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActionBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("액션바!");

        bar = getSupportActionBar();
        bar.setLogo(R.drawable.home);
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
        //액션바를 이미지로 변경
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 메뉴를 처음에 설정해주는 함수
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.menu_refresh:
                showToast("새로고침 메뉴 선택됨"); break;
            case R.id.menu_search:
                showToast("검색 메뉴 선택됨"); break;
            case R.id.menu_settings:
                showToast("설정 메뉴 선택됨"); break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showToast(String data){
        Toast.makeText(this,data,Toast.LENGTH_SHORT).show();
    }
}