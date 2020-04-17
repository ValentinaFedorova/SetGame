package com.example.setgame;

import java.util.ArrayList;

class ClearCard{
    int fill, count, shape, color;

    public ClearCard(int fill, int count, int shape, int color) {
        this.fill = fill;
        this.count = count;
        this.shape = shape;
        this.color = color;
    }
}

public class Request {

    String action, nickname;
    //ArrayList<Card> cards = new ArrayList<>();
    ArrayList<ClearCard> cards = new ArrayList<>();
    // предусмотреть конструкторы для каждого типа запросов (3 шт)
    public Request(String action, String nickname) {
        this.action = action;
        this.nickname = nickname;
    }

    public Request(String action,int token){
        this.action = action;
        this.token = token;
    }

    public Request(String action, int token, ArrayList<ClearCard> cards){
        this.action = action;
        this.token = token;
        this.cards = cards;
    }

    int token;

    public static Request RegisterRequest(String nick) {
        return new Request("register", nick);
    }

    public static Request FetchCardsRequest(int token) {
        return new Request("fetch_cards", token);
    }

    public static Request TakeSetRequest(int token,ArrayList<Card> set) {
        ArrayList<ClearCard> clearSet = new ArrayList<>();
        for (Card c: set) {
            ClearCard clearCard = new ClearCard(c.fill,c.count,c.shape,c.color);
            clearSet.add(clearCard);
        }
        return new Request("take_set", token,clearSet);
    }
}
