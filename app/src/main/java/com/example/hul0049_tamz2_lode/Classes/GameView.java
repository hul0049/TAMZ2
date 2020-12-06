package com.example.hul0049_tamz2_lode.Classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GameView extends View {

    public GameView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint tbPaint = new Paint();
        tbPaint.setColor(Color.BLACK);
        tbPaint.setTextSize(50);
        for(int i=0; i<10;i++){
            for(int j=0; j<10;j++){
                canvas.drawRect(j*10,i*10,j+1*10,i+1*10,tbPaint);

            }
        }
        super.onDraw(canvas);
    }
}
