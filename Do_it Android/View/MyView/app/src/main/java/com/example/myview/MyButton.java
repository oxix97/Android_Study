package com.example.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class MyButton extends AppCompatButton {
    //밑의 두개는 필수 생성자
    public MyButton(@NonNull Context context) {
        //버튼의 주변 환경을 담는 기능인 Context (기본형)
        super(context);

        init(context);
    }

    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        // AttributeSet은 xml안에 있는 속성을 포함해서 전달
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.CYAN);
        setTextColor(Color.BLACK);
        setTextSize(30.0f); // 픽셀 단위로 글자 크기를 지정
        float textSize = getResources().getDimension(R.dimen.text_size); // value폴더에 있는 text_size를 참조
        setTextSize(textSize);
    }

    @Override
    protected void onDraw(Canvas canvas) { //canvas는 모양을 그리는 함수를 제공
        super.onDraw(canvas);

        Log.d("MyButton","onDraw 호출됨");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyButton","onTouchEvent 호출됨");

        int action = event.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.BLUE);
                setTextColor(Color.RED); break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.CYAN);
                setTextColor(Color.BLUE); break;
        }

        invalidate(); // 화면에 보이는게 유효하지 않을 경우 다시 그려준다

        return true;
    }
}
