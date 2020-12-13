package com.example.hul0049_tamz2_lode.Classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.hul0049_tamz2_lode.Enums.Difficult;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameView extends View {

    private Player pl1;
    private Player pl2;
    private Board br1;
    private Board br2;
    private String text = null;
    private int x = 100;
    private int y = 100;
    private boolean win = false;
    private boolean rdy = false;
    private boolean rdyToPlace = true;
    private boolean pl = true; //kdyz je true hraje hrac 1
    private boolean bot = false;
    DBHelper mydb;

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
            if(win){
                tbPaint.setTextSize(150);
                if(br1.getShipalive()==0)
                {

                    canvas.drawText("Winner:", 320, 500, tbPaint);
                    canvas.drawText(pl2.getNick(), 320, 700, tbPaint);
                }
                else{
                    canvas.drawText("Winner:", 320, 500, tbPaint);
                    canvas.drawText(pl1.getNick(), 320, 700, tbPaint);
                }

            }
            else {

                int[] hits;
                if (pl) {
                    hits = br2.getHits();
                } else {
                    hits = br1.getHits();
                }

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (hits[i + 10 * j] == 0) {
                            tbPaint.setColor(Color.CYAN);
                        } else if (hits[i + 10 * j] == 1) {
                            tbPaint.setColor(Color.RED);
                        } else if (hits[i + 10 * j] == 2) {
                            tbPaint.setColor(Color.GRAY);
                        } else {
                            tbPaint.setColor(Color.BLUE);
                        }
                        canvas.drawRect(50 + i * 100, 200 + j * 100, 150 + i * 100, 300 + j * 100, tbPaint);

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
                tbPaint.setTextSize(100);
                if (text == null) {
                    text = pl1.getNick();
                }
                int a = 420;
                if (text.length() > 10) {
                    a -= 370;
                }
                canvas.drawText(text, a, 150, tbPaint);
                tbPaint.setTextSize(50);
                canvas.drawText(pl1.getNick() + " : " + br1.getShipalive(), 50, 1400, tbPaint);
                canvas.drawText(pl2.getNick() + " : " + br2.getShipalive(), 750, 1400, tbPaint);
            }
            super.onDraw(canvas);
        }
    }

    public void setLevel(Player pl1, Player pl2, Board br1, Board br2) {

        this.pl1=pl1;
        this.pl2=pl2;
        this.br1=br1;
        this.br2=br2;
        rdy = true;
        invalidate();
    }

    public void MakeMove(int myX, int myY) {
        if(rdyToPlace) {

            rdyToPlace=false;
            int[] hits;
            Ship[] ships;
            int[] shipX;
            int[] shipY;
            if (pl) {
                hits = br2.getHits();
                if (hits[myX + 10 * myY] == 0 || hits[myX + 10 * myY] == 1) {
                    rdyToPlace=true;
                    return;
                } else {
                    ships = pl2.getShips();
                    for(int i = 0; i < ships.length; i++) {
                        shipX = ships[i].getX();
                        shipY = ships[i].getY();
                        for (int j = 0; j < shipX.length; j++) {
                            if (shipX[j] == myX && shipY[j] == myY) {
                                ships[i].setLife(ships[i].getLife()-1);
                                if(ships[i].getLife()==0)
                                {
                                    text = "Zásah loď potopena";
                                    ShipSunk(ships[i]);
                                    if(br2.getShipalive()==0) {
                                        win = true;
                                    }
                                }
                                else {
                                    text = "Zásah";
                                    hits[myX + 10 * myY] = 1;
                                    br2.setHits(hits);
                                }
                                invalidate();
                            }
                        }
                    }
                    if (hits[myX + 10 * myY] == -1) {
                        text = "Minul";
                        hits[myX + 10 * myY] = 0;
                        br2.setHits(hits);
                        invalidate();
                    }

                }
            } else {
                hits = br1.getHits();
                if (hits[myX + 10 * myY] == 0 || hits[myX + 10 * myY] == 1) {
                    if(bot==true) {
                        MakeBotMove();
                    }
                    else {
                        return;
                    }
                } else {
                    ships = pl1.getShips();
                    for (int i = 0; i < ships.length; i++) {
                        shipX = ships[i].getX();
                        shipY = ships[i].getY();
                        for (int j = 0; j < shipX.length; j++) {
                            if (shipX[j] == myX && shipY[j] == myY) {
                                ships[i].setLife(ships[i].getLife()-1);
                                if(ships[i].getLife()==0)
                                {
                                    text = "Zásah loď potopena";
                                    ShipSunk(ships[i]);
                                    if(br1.getShipalive()==0)
                                        win=true;
                                }
                                else {
                                    text = "Zásah";
                                    hits[myX + 10 * myY] = 1;
                                    br1.setHits(hits);
                                }
                                invalidate();
                            }
                        }
                    }
                    if (hits[myX + 10 * myY] == -1) {
                        text = "Minul";
                        hits[myX + 10 * myY] = 0;
                        br1.setHits(hits);
                        invalidate();
                    }
                }
            }

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if(pl){
                        int[] h = br2.getHits();
                        if(h[myX + 10 * myY] == 0){
                           text=pl2.getNick();
                            pl=false;
                            if(pl2.getId()==-1)
                                bot=true;
                        }
                        else
                        text=pl1.getNick();
                    }
                    else
                    {
                        int[] h = br1.getHits();
                        if(h[myX + 10 * myY] == 0){
                            text=pl1.getNick();
                            pl=true;
                            if(pl2.getId()==-1)
                                bot=false;
                        }
                        else
                        text=pl2.getNick();
                    }
                    rdyToPlace=true;
                    invalidate();
                }
            }, 1500);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
            if(bot==true) {
                MakeBotMove();
                }
                }
            }, 2000);


        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:{
                if(bot&&!pl) {
                    return super.onTouchEvent(event);
                }
                else{
                    int myX = (int) ((event.getX() - 50) / 100);
                    int myY = (int) ((event.getY() - 200) / 100);
                    if ((myX < 10) && (myY < 10) && rdy) {
                        MakeMove(myX, myY);
                    }
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void ShipSunk(Ship ship) {
        if(pl){
            int hits[] = br2.getHits();
            int x[] = ship.getX();
            int y[] = ship.getY();
            for(int i=0;i<ship.getX().length;i++) {
                hits[x[i]+10*y[i]]=2;
            }
            br2.setHits(hits);
            br2.setShipalive(br2.getShipalive()-1);

        }else{
            int hits[] = br1.getHits();
            int x[] = ship.getX();
            int y[] = ship.getY();
            for(int i=0;i<ship.getX().length;i++) {
                hits[x[i]+10*y[i]]=2;
            }
            br1.setHits(hits);
            br1.setShipalive(br1.getShipalive()-1);
        }
    }

    public void MakeBotMove() {
        Random random = new Random();
        int x = -1;
        int y = -1;

        if(pl2.getDifficult() == Difficult.EASY){
            x = random.nextInt(br1.getSize());
            y = random.nextInt(br1.getSize());
        }
        else{
            int a = random.nextInt(10);
            if(a<3) {
                Ship ships[] = pl2.getShips();
                for(int i = 0; i < ships.length; i++) {
                    int shipX[] = ships[i].getX();
                    int shipY[]= ships[i].getY();
                    int Hits[] = br1.getHits();
                    for(int j=0 ;j<shipX.length;j++) {
                        if (Hits[shipX[j]+10*shipY[j]]==-1) {
                            x = shipX[j];
                            y = shipY[j];
                        }
                    }
                }
            }
            else{
                x = random.nextInt(br1.getSize());
                y = random.nextInt(br1.getSize());
            }
        }
        bot=true;
        MakeMove(x,y);
    }
    public boolean getWin() {
        return this.win;
    }
    public Player getWinner(){
        if(br1.getShipalive()==0)
            return pl2;
        else if(br2.getShipalive()==0)
            return pl1;
        return null;
    }
    public Player getLooser(){
        if(br1.getShipalive()==0)
            return pl1;
        else if(br2.getShipalive()==0)
            return pl2;
        return null;
    }
}
