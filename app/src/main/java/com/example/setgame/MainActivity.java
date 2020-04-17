package com.example.setgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btn;
    String nickname = "";
    int token;
    SetCardsFieldView cardsFieldView;
    ArrayList<Card> cards = new ArrayList<>();
    SetCardsFieldView view;
    SetViewActivity setViewActivity;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NICKNAME = "Nickname";
    SharedPreferences mSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.nick);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

//        setViewActivity = new SetViewActivity();
//        view = setViewActivity.setCardsFieldView;



    }

    public void authorized(){
        Intent intent = new Intent(MainActivity.this, SetViewActivity.class);
        //intent.putExtra("cards",cards);
        intent.putExtra("token",token);
        intent.putExtra("user_nick",nickname);
        startActivity(intent);
    }


    public void onRegisterClick(View view){
        nickname = et.getText().toString();

        if (nickname.length()!=0) {
//            if (mSettings.contains(APP_PREFERENCES_NICKNAME)){
//                Toast toast = Toast.makeText(getApplicationContext(), "Никнейм занят!", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//            else {
//
//                SharedPreferences.Editor editor = mSettings.edit();
//                editor.putString(APP_PREFERENCES_NICKNAME, nickname);
//                editor.apply();

                Request req = Request.RegisterRequest(nickname);
                SetServerTask task = new SetServerTask(this);
                //task.current_query = 0;
                task.execute(req);
            }


//
//            Toast toast = Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT);
//            toast.show();

            //отправить никнейм на сервер и получить токен

//            Intent intent = new Intent(MainActivity.this, SetViewActivity.class);
//            startActivity(intent);


        //}
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Введите никнейм", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
