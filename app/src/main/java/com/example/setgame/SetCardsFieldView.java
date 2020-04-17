package com.example.setgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class SetCardsFieldView extends View {


    int width,height;
    Context context;


    //ArrayList<Card> cards = new ArrayList<>();
    CardField cardField = new CardField();




    Paint p = new Paint();



    public SetCardsFieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        cardField.setCardsFieldView = this;

    }

    public SetCardsFieldView(Context context) {
        super(context);
    }

    public void makeToast(String text){
        context = getContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO: отобразить состояние карт
        width = canvas.getWidth();
        height = canvas.getHeight();
        cardField.drawCards(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO: в случе касания, определить, какой карты коснулись

        float x =  event.getX();
        float y = event.getY();
        cardField.cardTouched(x,y);
        invalidate();
        return super.onTouchEvent(event);
    }

    public float[] getCoordinations(int row,int column){
        float [] coords  = {column * (width / 4) + (width / 4) / 10, row * (height / 4) + (height / 4) / 10, column * (width / 4) + width / 4 - (width / 4) / 10, row * (height / 4) + height / 4 - (height / 4) / 10};
        return coords;
    }
}
