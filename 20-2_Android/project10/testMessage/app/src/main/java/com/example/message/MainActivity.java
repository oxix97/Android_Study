package com.example.message;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnSendMessage, btnCall, btnSearch, btnBrowse;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Message App");

        btnSendMessage = (Button)findViewById(R.id.bSend);
        btnCall = (Button)findViewById(R.id.bCall);
        btnSearch = (Button)findViewById(R.id.bSearch);
        btnBrowse = (Button)findViewById(R.id.bBrowse);
        editText = (EditText)findViewById(R.id.edit1);

        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body",editText.getText().toString());
                intent.setData(Uri.parse("smsto: "+Uri.encode("010-6206-1416")));
                startActivity(intent);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:"+editText.getText().toString());
                Intent intent = new Intent(Intent.ACTION_DIAL,uri);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,editText.getText().toString());
                startActivity(intent);
            }
        });
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://"+editText.getText().toString());
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }
}