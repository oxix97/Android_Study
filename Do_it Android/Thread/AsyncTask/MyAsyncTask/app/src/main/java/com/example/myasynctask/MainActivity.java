package com.example.myasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar bar;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = findViewById(R.id.progressBar);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundTask task = new BackgroundTask();
                task.execute();
            }
        });
    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer>{
        @Override
        protected void onPreExecute() { // 스레드로 실행되기 전 상태
            value = 0;
            bar.setProgress(value);
        }

        @Override
        protected void onPostExecute(Integer integer) { // 스레드로 실행된 후
            bar.setProgress(0);
        }

        @Override
        protected void onProgressUpdate(Integer... values) { // 중간에 스레드를 업데이트 할 경우
            bar.setProgress(values[0].intValue());
        }

        @Override
        protected Integer doInBackground(Integer... integers) { // 스레드로 실행되는 상태
            while(!isCancelled()){
                value+=1;
                if(value >=100) break;
            }
            publishProgress(value);
            try{
                Thread.sleep(1000);
            }catch (Exception e){

            }
            return value;
        }
    }

}