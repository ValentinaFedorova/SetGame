package com.example.setgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class SetViewActivity extends Activity {

    SetCardsFieldView setCardsFieldView;
    MainActivity mainActivity;
    ArrayList<Card> selected_cards;
    String str;
    int token;
    int points;
    int cards_left;
    String nick;
    TextView nickname,user_points;
    CardField cardField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_view);
        setCardsFieldView = findViewById(R.id.setview);
        //setCardsFieldView.context = getApplicationContext();
        cardField = new CardField();
        //cardField.context = getApplicationContext();
        token = getIntent().getIntExtra("token",0);
        nick = getIntent().getStringExtra("user_nick");
        //nickname = findViewById(R.id.nickname);
        //nickname.setText(nick);
        user_points = findViewById(R.id.points);
        user_points.setText("score: 0");
        askCards();
        //cards = (ArrayList<Card>)getIntent().getSerializableExtra("cards");
    }



    public void askCards(){
        Request req2 = Request.FetchCardsRequest(token);
        CardTask task = new CardTask(this);
        task.current_query = 1;
        task.execute(req2);
    }

    public void fetchCards(ArrayList<Card> cards){
        ArrayList<Card> testset = setCardsFieldView.cardField.findSet(cards);
        if (testset.size()!=0) {
            setCardsFieldView.cardField.setCard(cards);
            setCardsFieldView.cardField.card_context = getApplicationContext();
            setCardsFieldView.invalidate();
        }
        else {
            checkCardsLeft();
        }
        //cardField = new CardField(cards);
    }

    public void sendSet(){
        Request req = Request.TakeSetRequest(token,selected_cards);
        CardTask task = new CardTask(this);
        task.current_query = 2;
        task.execute(req);
    }

    public void onHintClick(View view){
        setCardsFieldView.cardField.hintSet();
    }

    public void onSentClick(View view){
        selected_cards = setCardsFieldView.cardField.isSelectedAreSet();
        if (selected_cards.size()!=0){
            sendSet();


        }
    }

    public void changePoints(){
        user_points.setText("score: " + points);
    }

    public void checkCardsLeft(){
        if (cards_left!=0){
            askCards();
        }
        else {
            setCardsFieldView.makeToast("Cards end");
        }
    }


}
