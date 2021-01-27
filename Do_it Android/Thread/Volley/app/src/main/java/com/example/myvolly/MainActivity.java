package com.example.myvolly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.FormatFlagsConversionMismatchException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;

    static RequestQueue queue;

    RecyclerView recyclerView;
    MovieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Volley 사용하기");

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

        adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

        editText.setText("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDaily" +
                "BoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&#38;targetDt=20120101");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlStr = editText.getText().toString();
                request(urlStr);
            }
        });

        queue = Volley.newRequestQueue(getApplicationContext());
    }

    private void request(String urlStr) {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                urlStr,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        print("응답 -> "+response);
                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        print("에러 -> "+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();

                return params;
            }
        };
        request.setShouldCache(false); // 리턴보낼때 마다 기존응답을 가져오는것을 차단
        queue.add(request);
        print("요청 보냄");
    }

    public void processResponse(String response) {
        Gson gson = new Gson();

        MovieList movieList = gson.fromJson(response,MovieList.class);
        print("영화 정보의 수 : "+movieList.boxOfficeResult.dailyBoxOfficeList.size());

        for(int i=0; i<movieList.boxOfficeResult.dailyBoxOfficeList.size();i++){
            Movie movie = movieList.boxOfficeResult.dailyBoxOfficeList.get(i);
            adapter.addItem(movie);
        }
        adapter.notifyDataSetChanged();
    }

    private void print(String data){
        textView.append(data + "\n");
    }
}