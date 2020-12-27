package com.example.sampleview;


import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyButton extends androidx.appcompat.widget.AppCompatButton{
    
    public MyButton(@NonNull Context context) {
        super(context);
        init(context);
    }
    //xml 레이아웃에 추가하는 경우 context 와 AttributSet 객체를 전달해 주어야한다.
    public MyButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("MyButton","onDraw 호출");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("MyButton","onTouchEvent 호출");
        
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.BLUE);
                setTextColor(Color.RED);
                break;
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.CYAN);
                setTextColor(Color.BLACK);
                break;
        }
        //다시 그래픽을 그려준다
        invalidate();
        return super.onTouchEvent(event);
    }

    private void init(Context context){
        setBackgroundColor(Color.CYAN);
        setTextColor(Color.BLACK);
        
        //res/values/dimens.xml -> text_size = 16sp
        float textSize = getResources().getDimension(R.dimen.text_size);
        setTextSize(textSize);
    }
}
