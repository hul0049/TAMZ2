package com.example.hul0049_tamz2_lode.Classes;

import com.example.hul0049_tamz2_lode.MainActivity;
import com.example.hul0049_tamz2_lode.Enums.Side;
import com.example.hul0049_tamz2_lode.Enums.Type;

import java.util.Random;

public class Board {
    private final int size;
    private Player player;
    private final int shipcount = 7;
    private int[] hits;

    public Board(int size, Player player) {
        this.size = size;
        this.player = player;
        hits = new int [size*size];
        for (int i = 0 ; i<size*size ; i++)
        {
            hits[i]=-1;
        }

    }

    public void InitializeBoard() {
        SetShips(player);
    }

    private boolean IsPossible(int x, int y) {
       if(x >= size || y >= size || x < 0 || y < 0)
           return false;

        Ship[] ships = player.getShips();

       for(int i = 0; i<shipcount; i++) {
           if(ships[i]!=null)
           {
               int[] shipX = ships[i].getX();
               int[] shipY = ships[i].getY();

               for (int j = 0; j<shipX.length; j++) {
                   if (x == shipX[j]  && y == shipY[j])
                       return false;
                   else if (x + 1 == shipX[j] && y == shipY[j] || x - 1 == shipX[j] && y == shipY[j]
                           || x == shipX[j] && y + 1 == shipY[j] || x == shipX[j] && y - 1 == shipY[j] )
                       return false;
           }
           }
            else
                break;
       }
        return true;
    }

    private boolean IsAdjustable(int x, int y, Type type, Side side) {

        if (!IsPossible(x, y))
            return false;

            switch (type) {
                case MAIN: {
                    switch (side) {
                        case RIGHT: {

                            if (!IsPossible(x + 1, y))
                                return false;
                            else if (!IsPossible(x + 2, y))
                                return false;
                            else if (!IsPossible(x + 3, y))
                                return false;
                            else if (!IsPossible(x + 1, y + 1)||!IsPossible(x + 2, y + 1)) {
                                return IsPossible(x + 1, y - 1) && IsPossible(x + 2, y - 1);
                            }
                            else
                                return true;
                        }
                    case LEFT: {
                        if (!IsPossible(x - 1, y))
                            return false;
                        else if (!IsPossible(x - 2, y))
                            return false;
                        else if (!IsPossible(x - 3, y))
                            return false;
                        else if (!IsPossible(x - 1, y + 1) || !IsPossible(x - 2, y + 1)) {
                            return IsPossible(x - 1, y - 1) && IsPossible(x - 2, y - 1);
                        }
                        else
                            return true;
                    }
                    case UP: {
                        if (!IsPossible(x, y + 1))
                            return false;
                        else if (!IsPossible(x , y + 2))
                            return false;
                        else if (!IsPossible(x , y + 3))
                            return false;
                        else if (!IsPossible(x + 1, y + 1)||!IsPossible(x + 2, y + 2)) {
                            return IsPossible(x - 1, y + 1) && IsPossible(x - 2, y + 1);
                        }
                        else
                            return true;
                    }
                    case DOWN:{
                        if (!IsPossible(x, y - 1))
                            return false;
                        else if (!IsPossible(x , y - 2))
                            return false;
                        else if (!IsPossible(x , y - 3))
                            return false;
                        else if (!IsPossible(x + 1, y - 1)||!IsPossible(x + 2, y - 2)){
                            return IsPossible(x - 1, y - 1) && IsPossible(x - 2, y - 2);
                        }
                        else
                            return true;
                    }

                    default:
                        return false;
                }
            }

            case NORMAL:{
                switch (side) {
                    case RIGHT: {

                    if (!IsPossible(x + 1, y))
                        return false;
                    else if (!IsPossible(x + 2, y))
                        return false;
                    else if (!IsPossible(+ 1, y + 1)){
                        return IsPossible(x + 1, y - 1);
                    }
                    else
                        return true;
                }
                    case LEFT: {
                    if (!IsPossible(x - 1, y))
                        return false;
                    else if (!IsPossible(x - 2, y))
                        return false;
                    else if (!IsPossible(x - 1, y + 1)) {
                        return IsPossible(x - 1, y - 1);
                    }
                    else
                        return true;
                    }
                    case UP: {
                    if (!IsPossible(x, y + 1))
                        return false;
                    else if (!IsPossible(x , y + 2))
                        return false;
                    else if (!IsPossible(x + 1, y + 1)) {
                        return IsPossible(x - 1, y + 1);
                    }
                    else
                        return true;
                }
                    case DOWN:{
                    if (!IsPossible(x, y - 1))
                        return false;
                    else if (!IsPossible(x , y - 2))
                        return false;
                    else if (!IsPossible(x + 1, y - 1)){
                        return IsPossible(x - 1, y - 1);
                    }
                    else
                        return true;
                }

                default:
                    return false;
            }
            }

            case STRAIGHT: switch (side) {
                case RIGHT: {

                    if (!IsPossible(x + 1, y))
                        return false;
                    else if (!IsPossible(x + 2, y))
                        return false;
                    else return IsPossible(x + 3, y);
                }
                case LEFT: {
                    if (!IsPossible(x - 1, y))
                        return false;
                    else if (!IsPossible(x - 2, y))
                        return false;
                    else return IsPossible(x - 3, y);
                }
                case UP: {
                    if (!IsPossible(x, y + 1))
                        return false;
                    else if (!IsPossible(x , y + 2))
                        return false;
                    else return IsPossible(x, y + 3);
                }
                case DOWN:{
                    if (!IsPossible(x, y - 1))
                        return false;
                    else if (!IsPossible(x , y - 2))
                        return false;
                    else return IsPossible(x, y - 3);
                }
                default:
                    return false;
            }

            case SMALL:switch (side){
                case RIGHT: {
                    return IsPossible(x + 1, y);
                }
                case LEFT: {
                    return IsPossible(x - 1, y);
                }
                case UP: {
                    return IsPossible(x, y + 1);
                }
                case DOWN:{
                    return IsPossible(x, y - 1);
                }
                default:
                    return false;
            }
            }
            return false;
    }

    private void SetShips(Player player){
        Random random = new Random();
        int x,y;
        Type type = null;
        for (int i = 0; i < shipcount; i++) {
            Side side = randomSide();
            if(i==0)
                 type = Type.MAIN;
            else if(i<3)
                type = Type.NORMAL;
            else if(i<5)
                type = Type.STRAIGHT;
            else if(i<7)
                type = Type.SMALL;

            x = random.nextInt(size);
            y = random.nextInt(size);
            if(IsAdjustable(x,y,type,side))
            {
                int a[];
                int b[];
                switch (type)
                {
                    case MAIN: {
                        a = new int[6];
                        b = new int[6];
                        switch (side) {
                            case RIGHT:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x+1;
                                b[1]=y;
                                a[2]=x+2;
                                b[2]=y;
                                a[3]=x+3;
                                b[3]=y;
                                if (!IsPossible(x + 1, y + 1)||!IsPossible(x + 2, y + 1)) {
                                    a[4] = x + 1;
                                    b[4] = y - 1;
                                    a[5] = x + 2;
                                    b[5] = y - 1;
                                }
                                else{
                                    a[4] = x + 1;
                                    b[4] = y + 1;
                                    a[5] = x + 2;
                                    b[5] = y + 1;
                                }
                            }break;
                            case LEFT:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x-1;
                                b[1]=y;
                                a[2]=x-2;
                                b[2]=y;
                                a[3]=x-3;
                                b[3]=y;
                                if (!IsPossible(x - 1, y + 1)||!IsPossible(x - 2, y + 1)) {
                                    a[4] = x - 1;
                                    b[4] = y - 1;
                                    a[5] = x - 2;
                                    b[5] = y - 1;
                                }
                                else{
                                    a[4] = x - 1;
                                    b[4] = y + 1;
                                    a[5] = x - 2;
                                    b[5] = y + 1;
                                }
                            }break;
                            case UP:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x;
                                b[1]=y+1;
                                a[2]=x;
                                b[2]=y+2;
                                a[3]=x;
                                b[3]=y+3;
                                if (!IsPossible(x + 1, y + 1)||!IsPossible(x + 1, y + 2)) {
                                    a[4] = x - 1;
                                    b[4] = y + 1;
                                    a[5] = x - 1;
                                    b[5] = y + 2;
                                }
                                else{
                                    a[4] = x + 1;
                                    b[4] = y + 1;
                                    a[5] = x + 1;
                                    b[5] = y + 2;
                                }
                            }break;
                            case DOWN:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x;
                                b[1]=y-1;
                                a[2]=x;
                                b[2]=y-2;
                                a[3]=x;
                                b[3]=y-3;
                                if (!IsPossible(x + 1, y - 1)||!IsPossible(x + 1, y - 2)) {
                                    a[4] = x - 1;
                                    b[4] = y - 1;
                                    a[5] = x - 1;
                                    b[5] = y - 2;
                                }
                                else{
                                    a[4] = x + 1;
                                    b[4] = y - 1;
                                    a[5] = x + 1;
                                    b[5] = y - 2;
                                }
                            }break;
                        }
                        break;
                    }
                    case NORMAL: {
                        a = new int[4];
                        b = new int[4];
                        switch (side) {
                            case RIGHT:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x+1;
                                b[1]=y;
                                a[2]=x+2;
                                b[2]=y;
                                if(!IsPossible(x + 1, y + 1)){
                                    a[3] = x + 1;
                                    b[3] = y - 1;
                                }
                                else{
                                    a[3] = x + 1;
                                    b[3] = y + 1;
                                }
                            }break;

                            case LEFT:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x-1;
                                b[1]=y;
                                a[2]=x-2;
                                b[2]=y;
                                if(!IsPossible(x - 1, y + 1)){
                                    a[3] = x - 1;
                                    b[3] = y - 1;
                                }
                                else{
                                    a[3] = x - 1;
                                    b[3] = y + 1;
                                }
                            }break;

                            case UP:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x;
                                b[1]=y+1;
                                a[2]=x;
                                b[2]=y+2;
                                if(!IsPossible(x + 1, y + 1)){
                                    a[3] = x - 1;
                                    b[3] = y + 1;
                                }
                                else{
                                    a[3] = x + 1;
                                    b[3] = y + 1;
                                }
                            }break;

                            case DOWN:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x;
                                b[1]=y-1;
                                a[2]=x;
                                b[2]=y-2;
                                if(!IsPossible(x + 1, y - 1)){
                                    a[3] = x - 1;
                                    b[3] = y - 1;
                                }
                                else{
                                    a[3] = x + 1;
                                    b[3] = y - 1;
                                }
                            }break;
                        }
                        break;
                    }
                    case STRAIGHT:{
                        a = new int[4];
                        b = new int[4];
                        switch (side) {
                            case RIGHT:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x+1;
                                b[1]=y;
                                a[2]=x+2;
                                b[2]=y;
                                a[3]=x+3;
                                b[3]=y;
                            }break;
                            case LEFT:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x-1;
                                b[1]=y;
                                a[2]=x-2;
                                b[2]=y;
                                a[3]=x-3;
                                b[3]=y;
                            }break;
                            case UP:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x;
                                b[1]=y+1;
                                a[2]=x;
                                b[2]=y+2;
                                a[3]=x;
                                b[3]=y+3;
                            }break;
                            case DOWN:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x;
                                b[1]=y-1;
                                a[2]=x;
                                b[2]=y-2;
                                a[3]=x;
                                b[3]=y-3;
                            }break;
                        }
                        break;
                    }
                    case SMALL:{
                        a = new int[2];
                        b = new int[2];
                        switch (side) {
                            case RIGHT:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x+1;
                                b[1]=y;
                            }break;
                            case LEFT:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x-1;
                                b[1]=y;
                            }break;
                            case UP:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x;
                                b[1]=y+1;
                            }break;
                            case DOWN:{
                                a[0]=x;
                                b[0]=y;
                                a[1]=x;
                                b[1]=y-1;
                            }break;
                        }
                        break;
                    }
                    default:  return;
                }
                Ship ship = new Ship(a,b,type);
                player.setShips(ship,i);
            }
            else
                i--;
        }
    }

    private Side randomSide() {
        int pick = new Random().nextInt(Side.values().length);
        return Side.values()[pick];
    }

    private Type randomType() {
        int pick = new Random().nextInt(Type.values().length);
        return Type.values()[pick];
    }

    public int[] getHits() {
        return hits;
    }

    public void setHits(int[] hits) {
        this.hits = hits;
    }

    public int getShipcount() {
        return shipcount;
    }
}
