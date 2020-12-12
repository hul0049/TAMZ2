package com.example.hul0049_tamz2_lode.Classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hul0049_tamz2_lode.R;

public class GameView extends View {

    private Player pl1;
    private Player pl2;
    private Board br1;
    private Board br2;
    private int x = 100;
    private int y = 100;
    private boolean rdy = false;
    private boolean pl = true; //kdzy je true hraje hrac 1
    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(rdy) {
            Paint tbPaint = new Paint();
            int[] hits;
            if(pl) {
                hits = br2.getHits();
            }
            else {
                hits = br1.getHits();
            }

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if(hits[i+10*j]==0) {
                        tbPaint.setColor(Color.CYAN);
                    }
                    else if(hits[i+10*j]==1){
                        tbPaint.setColor(Color.RED);
                    }else{
                        tbPaint.setColor(Color.BLUE);
                    }
                    canvas.drawRect(150 + j * 100, 300 + i * 100, 50, 200, tbPaint);
                }
            }
            tbPaint.setStrokeWidth(10);
            tbPaint.setColor(Color.BLACK);
            for (int i = 0; i < 11; i++) {
                canvas.drawLine(50 + i * 100, 200, 50 + i * 100, 1200, tbPaint);
            }
            for (int i = 0; i < 11; i++) {
                canvas.drawLine(50, 200 + i * 100, 1050, 200 + i * 100, tbPaint);
            }
            super.onDraw(canvas);
        }
    }

    public void setLevel(Player pl1, Player pl2, Board br1, Board br2)
    {

        this.pl1=pl1;
        this.pl2=pl2;
        this.br1=br1;
        this.br2=br2;
        rdy = true;
        invalidate();
    }
    public void MakeMove(int myX, int myY, boolean pl)
    {
        int[] hits;
        Ship[] ships;
        int[] shipX;
        int[] shipY;
        if(pl) {
            hits = br2.getHits();


            if (hits[myX + 10 * myY] == 0 || hits[myX + 10 * myY] == 1) {
                return;
            } else {
                ships = pl2.getShips();
                for (int i = 0; i < ships.length; i++) {
                    shipX = ships[i].getX();
                    shipY = ships[i].getY();
                    for (int j = 0; j < shipX.length; j++) {
                        if (shipX[j] == myX && shipY[j] == myY) {
                            hits[myX + 10 * myY] = 1;
                            br2.setHits(hits);
                            invalidate();
                            return;
                        }
                    }
                    hits[myX + 10 * myY] = 0;
                    br2.setHits(hits);
                    invalidate();
                    return;
                }
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:{


                int myX = (int) ((event.getX()-50)/100);
                int myY = (int) ((event.getY()-200)/100);
                Toast.makeText(getContext(),"x: "+myX+"  y: "+myY,Toast.LENGTH_LONG).show();
                if ((myX<10) && (myY<10) && rdy)
                {
                    MakeMove(myX,myY,pl);
                }
            }
        }

        return super.onTouchEvent(event);
    }
}
