package com.example.setgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Objects;

public class Card {
    int fill, count, shape, color;
    int row,column;
    float left,right,top,bottom;
    int cardWidth = 10;
    int cardHeight = 10;
    boolean isPicked = false;
    int cnt_pick = 0;
    Paint p = new Paint();

    public Card(int fill, int count, int shape, int color, int row, int column) {
        this.fill = fill;
        this.count = count;
        this.shape = shape;
        this.color = color;
        this.row = row;
        this.column = column;
    }

    public Card(){

    }

    public float[] getCoordinations(int row,int column,Canvas canvas){
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float [] coords  = {column * (width / 4) + (width / 4) / 10, row * (height / 4) + (height / 4) / 10, column * (width / 4) + width / 4 - (width / 4) / 10, row * (height / 4) + height / 4 - (height / 4) / 10};
        return coords;
    }
    public float wx(double cx){
        return (float) ((right-left) * (cx/cardWidth) + left);
    }
    public float wy(double cy){
        return (float) ((bottom-top) * (cy/cardHeight) + top);
    }

    public float wd(double d){
        return (float)((bottom-top) * (d/cardHeight));
    }

    public void drawSmallCircles(float x,float y, Paint p,Canvas c){
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth((float)1.5);
        float radius;

        for (int i = 0;i<4;i++) {
            radius = wd(1.0-i/4.0);
            c.drawCircle(x,y, radius, p);
        }
    }

    public void drawSmallRects(float x1,float y1, float x2, float y2 , Paint p, Canvas c){
        p.setStyle(Paint.Style.STROKE);
        //p.setStrokeWidth((float)1.5);
        float l,t,r,b;
        float dx,dy;
        dx = (float) ((x2-x1)/(8.0));
        dy = (float) ((y2-y1)/(8.0));
        c.drawRect(x1,y1,x2,y2,p);
        for(int i=0;i<4;i++){
            l = x1 + dx;
            r = x2 - dx;
            x1 = l;
            x2 = r;
            t = y1 + dy;
            b = y2 - dy;
            y1 = t;
            y2 = b;

            c.drawRect(l,t,r,b,p);

        }
    }

    public void drawSmallRoundRects(float x1,float y1, float x2, float y2 , Paint p, Canvas c){
        p.setStyle(Paint.Style.STROKE);
        //p.setStrokeWidth((float)1.5);
        float l,t,r,b;
        float dx,dy;
        dx = (float) ((x2-x1)/(8.0));
        dy = (float) ((y2-y1)/(8.0));
        RectF rect = new RectF();
        rect.set(x1,y1,x2,y2);
        c.drawRoundRect(rect,150, 40, p);
        for(int i=0;i<4;i++){
            l = x1 + dx;
            r = x2 - dx;
            x1 = l;
            x2 = r;
            t = y1 + dy;
            b = y2 - dy;
            y1 = t;
            y2 = b;

            rect.set(l,t,r,b);
            c.drawRoundRect(rect,150, 40, p);


        }
    }


    public boolean touched (float touch_x, float touch_y) {

        //float [] coordinates = getCoordinations(row,column);
        if (touch_x >= left && touch_x <= right && touch_y >= top && touch_y <= bottom) {
            //isOpen = ! isOpen;
//            if (cnt_pick==0) {
//                //isPicked = true;
//                //cnt_pick = 1;
//                return 1;
//            }
//            else {
//                //isPicked = false;
//                //cnt_pick = 0;
//                return 2;
//            }
            return true;
        }
        return false;
    }




    public void drawCard(Canvas c){
        float [] coord = getCoordinations(row,column,c);
        int width = c.getWidth();
        int height = c.getHeight();
        left = coord[0];
        top = coord[1];
        right = coord[2];
        bottom = coord[3];
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);
        RectF cardRect = new RectF();
        if (isPicked){
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(8);
            p.setColor(Color.YELLOW);
            cardRect.set(wx(0),wy(0),wx(10),wy(10));
            c.drawRoundRect(cardRect,20,20,p);
            p.setStyle(Paint.Style.FILL);
            int backgr = Color.rgb(255, 251, 237);
            p.setColor(backgr);
            c.drawRoundRect(cardRect,20,20,p);


        }
        else {
            p.setColor(Color.WHITE);
            p.setStrokeWidth(1);
            cardRect.set(wx(0.125),wy(0.125),wx(9.875),wy(9.875));
            c.drawRoundRect(cardRect,20,20,p);
        }

        p.setColor(Color.WHITE);


        p.setStrokeWidth(1);



        //c.drawRect(wx(0.25),wy(0.25),wx(9.75),wy(9.75),p);

        //c.drawRect(wx(0.5),wy(0.5),wx(9.5),wy(9.5),p);


        //drawSmallCircles(wx(5),wy(5),p,c);
        //p.setColor(Color.GREEN);

        //c.drawRect(wx(2.5),wy(4),wx(7.5),wy(6),p);



//        c.drawCircle(wx(5),wy(5),wd(1),p);
//        c.drawCircle(wx(5),wy(3),wd(1),p);
//        c.drawCircle(wx(5),wy(7),wd(1),p);


        if (color==1) {
            int first_color = Color.rgb(121, 8, 170);
            p.setColor(first_color);
        }
        else if (color==2){
            int second_color = Color.rgb(149, 236, 0);
            p.setColor(second_color);
        }
        else if (color==3){
            int third_color = Color.rgb(255, 215, 0);
            p.setColor(third_color);
        }
        if (fill==1) {
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(2);
        }
        else if (fill==2){
            p.setStyle(Paint.Style.FILL);
        }

        if (shape==1){
            if (count==1){
                if (fill==3){
                    drawSmallCircles(wx(5),wy(5),p,c);
                }
                else {
                    c.drawCircle(wx(5), wy(5), wd(1), p);
                }
            }
            else if (count==2){
                if (fill==3){
                    drawSmallCircles(wx(5), wy(3.5), p,c);
                    drawSmallCircles(wx(5), wy(6.5), p,c);
                }
                else {
                    c.drawCircle(wx(5), wy(3.5), wd(1), p);
                    c.drawCircle(wx(5), wy(6.5), wd(1), p);
                }
            }
            else if (count==3){
                if (fill==3){
                    drawSmallCircles(wx(5), wy(5), p,c);
                    drawSmallCircles(wx(5), wy(2.5), p,c);
                    drawSmallCircles(wx(5), wy(7.5), p,c);
                }
                else {
                    c.drawCircle(wx(5), wy(5), wd(1), p);
                    c.drawCircle(wx(5), wy(2.5), wd(1), p);
                    c.drawCircle(wx(5), wy(7.5), wd(1), p);
                }

            }
        }
        else if (shape==2){
            if (count==1){
                if (fill==3){
                    drawSmallRects(wx(2.5), wy(4), wx(7.5), wy(6), p,c);
                }

                else {
                    c.drawRect(wx(2.5), wy(4), wx(7.5), wy(6), p);
                }
            }
            else if (count==2){
                if (fill==3){
                    drawSmallRects(wx(2.5), wy(2.5), wx(7.5), wy(4.5), p,c);
                    drawSmallRects(wx(2.5), wy(5.5), wx(7.5), wy(7.5), p,c);
                }
                else {
                    c.drawRect(wx(2.5), wy(2.5), wx(7.5), wy(4.5), p);
                    c.drawRect(wx(2.5), wy(5.5), wx(7.5), wy(7.5), p);
                }
            }
            else if (count==3){
                if (fill==3){
                    drawSmallRects(wx(2.5), wy(1.5), wx(7.5), wy(3.5), p,c);
                    drawSmallRects(wx(2.5), wy(4), wx(7.5), wy(6), p,c);
                    drawSmallRects(wx(2.5), wy(6.5), wx(7.5), wy(8.5), p,c);
                }
                else {
                    c.drawRect(wx(2.5), wy(1.5), wx(7.5), wy(3.5), p);
                    c.drawRect(wx(2.5), wy(4), wx(7.5), wy(6), p);
                    c.drawRect(wx(2.5), wy(6.5), wx(7.5), wy(8.5), p);
                }
            }
        }
        else if (shape==3){
            RectF rect = new RectF();
            if (count==1){
                if (fill==3){
                    drawSmallRoundRects(wx(3.5), wy(4), wx(6.5), wy(6),p,c);
                }
                else {

                    rect.set(wx(3.5), wy(4), wx(6.5), wy(6));
                    c.drawRoundRect(rect, 150, 40, p);
                }
            }
            else if (count==2){
                if (fill==3){

                    drawSmallRoundRects(wx(3.5), wy(2.5), wx(6.5), wy(4.5),p,c);
                    drawSmallRoundRects(wx(3.5), wy(5.5), wx(6.5), wy(7.5),p,c);
                }
                else {
                    rect.set(wx(3.5), wy(2.5), wx(6.5), wy(4.5));
                    c.drawRoundRect(rect, 150, 40, p);

                    rect.set(wx(3.5), wy(5.5), wx(6.5), wy(7.5));
                    c.drawRoundRect(rect, 150, 40, p);
                }
            }
            else if (count==3){
                if (fill==3){
                    drawSmallRoundRects(wx(3.5), wy(1.5), wx(6.5), wy(3.5),p,c);
                    drawSmallRoundRects(wx(3.5), wy(4), wx(6.5), wy(6),p,c);
                    drawSmallRoundRects(wx(3.5), wy(6.5), wx(6.5), wy(8.5),p,c);

                }
                else {
                    rect.set(wx(3.5), wy(1.5), wx(6.5), wy(3.5));
                    c.drawRoundRect(rect, 150, 40, p);

                    rect.set(wx(3.5), wy(4), wx(6.5), wy(6));
                    c.drawRoundRect(rect, 150, 40, p);

                    rect.set(wx(3.5), wy(6.5), wx(6.5), wy(8.5));
                    c.drawRoundRect(rect, 150, 40, p);
                }
            }
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(fill, count, shape, color);
    }
    @Override

    public boolean equals(Object o){
        Card card = (Card) o;


        if (card.fill == this.fill && card.color == this.color && card.count == this.count && card.shape == this.shape){
            return true;
        }
        return false;

    }


    public Card getThird(Card card){
        Card thirdCard = new Card();
        int sum = 6;
        int cur_sum = 0;

        if (card.count == this.count){
            thirdCard.count = card.count;
        }
        else {
            cur_sum = card.count + this.count;
            thirdCard.count = sum - cur_sum;
            cur_sum = 0;
        }

        if (card.color == this.color){
            thirdCard.color = card.color;
        }
        else {
            cur_sum = card.color + this.color;
            thirdCard.color = sum - cur_sum;
            cur_sum = 0;
        }

        if (card.shape == this.shape){
            thirdCard.shape = card.shape;
        }
        else {
            cur_sum = card.shape + this.shape;
            thirdCard.shape = sum - cur_sum;
            cur_sum = 0;
        }

        if (card.fill == this.fill){
            thirdCard.fill = card.fill;
        }
        else {
            cur_sum = card.fill + this.fill;
            thirdCard.fill = sum - cur_sum;
            cur_sum = 0;
        }

        return thirdCard;

    }

    public String toStringCard(){
        String[] colors  = new String[] {"blue", "green", "red"};
        String[] shapes  = new String[] {"rect", "rhombus", "wave"};
        String[] fills  = new String[] {"contour", "hatch", "full"};
        String color = "";
        String shape = "";
        String fill = "";
        switch (this.color){
            case 1:
                color = colors[0];
                break;
            case 2:
                color = colors[1];
            case 3:
                color = colors[2];

        }

        switch (this.shape){
            case 1:
                shape = shapes[0];
                break;
            case 2:
                shape = shapes[1];
            case 3:
                shape = shapes[2];

        }

        switch (this.fill){
            case 1:
                fill = fills[0];
                break;
            case 2:
                fill = fills[1];
            case 3:
                fill = fills[2];

        }
        String result = "Card: fill = " + this.fill + ", count = " + this.count + ", shape = " +this.shape+", color = " + this.color;
        return result;

    }

}
