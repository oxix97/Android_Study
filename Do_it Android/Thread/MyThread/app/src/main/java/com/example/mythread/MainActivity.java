package com.example.mythread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    //MainHandler handler;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });
      //  handler = new MainHandler();
    }
    class BackgroundThread extends Thread{
        int value = 0;
        public void run(){
            for(int i = 0; i < 100; i++){

                try{
                    Thread.sleep(1000);
                }catch (Exception e){ }

                value++;
                Log.d("MyThread","value : " + value);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("값 : "+value);
                    }
                });

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 5000); // 약간의 지연시간을 가지고 실행한다.

               // textView.setText("값 : "+value); -> 스레드에 문제가 생김
            /*
                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value",value);
                message.setData(bundle);

                handler.sendMessage(message);

             */
            }
        }
    }
/*
    class MainHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");

            textView.setText("값 : "+value);
        }
    }
 */

}



