package com.example.setgame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CardField {

    ArrayList<Card> cards;
    ArrayList<Card> selectedCards = new ArrayList<>();
    SetViewActivity setViewActivity;
    SetCardsFieldView setCardsFieldView;
    Context card_context;

    public void setCard(ArrayList<Card> setcards){
        cards = setcards;
        int id = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                cards.get(id).column = j;
                cards.get(id).row = i;

                id++;


            }

        }
    }

    public CardField(){

    }

    public void drawCards(Canvas canvas){
        if (cards!=null) {
            for (Card c : cards) {
                c.drawCard(canvas);
            }
        }
    }

    public int pickedCardCounter(){
        int cnt = 0;
        for (Card c: cards) {
            if (c.isPicked){
                cnt++;
            }
        }
        return cnt;
    }

    public void cardTouched(float x, float y) {
        //int isTouched;
        int pickedCardAmount = pickedCardCounter();
        if (cards!=null) {
            if (pickedCardAmount<=3) {
                for (Card c : cards) {
                    if (c.touched(x, y)) {
                        if (pickedCardAmount==3){
                            if (c.isPicked==true){
                                c.isPicked = false;
                                break;
                            }
                            else {
                                setCardsFieldView.makeToast("В сете не может быть более 3-х карт!");
                                break;
                            }
                        }

                        else {
                            c.isPicked = !c.isPicked;
                            break;
                        }
                    }
                }
            }

        }
    }

    public void allCardsUnpicked(){
        for (Card c: cards) {
            c.isPicked = false;
        }
    }

    public ArrayList<Card> findSet(ArrayList<Card> cards_to_check){
        Card thirdCard;
        Set<Card> cardSet = new HashSet<Card>();
        for (int i = 0; i < cards_to_check.size(); i++) {
            cardSet.add(cards_to_check.get(i));
        }
        ArrayList<Card> set_card = new ArrayList<>();

        outterLoop: for (int i = 0; i < cards_to_check.size() - 1; i++) {
            for (int j = i + 1; j < cards_to_check.size(); j++) {

                thirdCard = cards_to_check.get(i).getThird(cards_to_check.get(j));
                if (cardSet.contains(thirdCard)) {
                    int pos = cards_to_check.indexOf(thirdCard);

                    set_card.add(cards_to_check.get(i));
                    set_card.add(cards_to_check.get(j));
                    set_card.add(cards_to_check.get(pos));

                    break outterLoop;


                }


            }
        }
        return set_card;
    }

    public void hintSet(){
        allCardsUnpicked();

//        Card thirdCard;
//        Set<Card> cardSet = new HashSet<Card>();
//        for (int i = 0; i < cards.size(); i++) {
//            cardSet.add(cards.get(i));
//        }
       ArrayList<Card> set_card = new ArrayList<>();
//
//        outterLoop: for (int i = 0; i < cards.size() - 1; i++) {
//            for (int j = i + 1; j < cards.size(); j++) {
//
//                thirdCard = cards.get(i).getThird(cards.get(j));
//                if (cardSet.contains(thirdCard)) {
//                    int pos = cards.indexOf(thirdCard);
//
//                    set_card.add(cards.get(i));
//                    set_card.add(cards.get(j));
//                    set_card.add(cards.get(pos));
//
//                    break outterLoop;
//
//
//                }
//
//
//            }
//        }
        set_card = findSet(cards);
        for (int i=0;i<set_card.size();i++){
            set_card.get(i).isPicked = !set_card.get(i).isPicked;
        }
        setCardsFieldView.invalidate();
    }

    public ArrayList<Card> isSelectedAreSet () {
        int pickedCardAmount = pickedCardCounter();
        ArrayList<Card> selectedCards = new ArrayList<>();
        if (pickedCardAmount==3){

            for (Card c: cards) {
                if (c.isPicked==true){
                    selectedCards.add(c);
                }
            }

            Card thirdCard = new Card();

            thirdCard = selectedCards.get(0).getThird(selectedCards.get(1));
            if (thirdCard.equals(selectedCards.get(2))){
                setCardsFieldView.makeToast("Это сет!");

                //sent to server
            }
            else {
                setCardsFieldView.makeToast("Это не сет!");
                selectedCards.clear();
                allCardsUnpicked();
                setCardsFieldView.invalidate();
            }

        }
        else {
            setCardsFieldView.makeToast("В сете не может быть менее 3-х карт!");
        }
        return selectedCards;
    }
}
