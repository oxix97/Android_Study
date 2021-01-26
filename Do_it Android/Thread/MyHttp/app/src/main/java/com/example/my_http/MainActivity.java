package com.example.my_http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String urlStr = editText.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(urlStr);
                    }
                }).start();
            }
        });
    }

    private void request(String urlStr) {
        try{
            StringBuilder builder = new StringBuilder();
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(connection != null){
                connection.setConnectTimeout(10000); // 연결이 해당시간동안 안되면 끊음
                connection.setRequestMethod("GET"); // GET방식의 메서드
                connection.setDoInput(true); // 요청을보내고 받는것 중에 받는것을 설정

                int resCode = connection.getResponseCode(); // 응답받은 것

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;

                while(true){
                    line = reader.readLine();
                    if(line == null) break;
                    builder.append(line + "\n");
                }

                reader.close();
                connection.disconnect();
            }
            println("응답 -> "+builder.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });
    }
}