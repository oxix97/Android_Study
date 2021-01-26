package com.example.mysocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText input1;
    TextView output1;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        input1 = findViewById(R.id.input1);
        output1 = findViewById(R.id.output1);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String data = input1.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });

        Button startServerButton = findViewById(R.id.startServerButton);
        startServerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start(); // .start()를 하지 않으면 실행이 안됨.
            }
        });
    }
    private void println(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                output1.append(data + "\n");
            }
        });

    }
    private void startServer() {
        int port = 5001;

        try{
           ServerSocket serverSocket =  new ServerSocket(port);

           while(true){
               Socket socket = serverSocket.accept();
               InetAddress clientHost = socket.getLocalAddress();
               int clientPort = socket.getPort();
               println("클라이언트 연결됨 : "+clientHost + ", "+ clientPort);

               ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
               String input = (String) inputStream.readObject();
               println("데이터 받음 : "+input);

               ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
               outputStream.writeObject(input + "from server.");
               println("데이터 보냄 ");

               socket.close();
           }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void send(String data) {
        int port = 5001;

        try{
            Socket socket = new Socket("localhost",port);
            ObjectOutputStream outputStream =  new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(data);
            outputStream.flush();

            ObjectInputStream inputStream = (ObjectInputStream) socket.getInputStream();
            String input = (String)inputStream.readObject();
            socket.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}