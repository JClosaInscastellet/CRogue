package com.example.crogue;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.os.Handler;

public class GameView extends View {
    Paint textPaint = new Paint();
    static int dWidth,dHeigth;
    Handler handler;
    Runnable runnable;
    public GameView(Context context) {
        super(context);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeigth = size.y;
        handler = new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                textPaint.setColor(Color.rgb(255,255,0));
                textPaint.setTextSize(120);

            }
        };
    }
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawText("Hello World",20,20,textPaint);
    }

}
