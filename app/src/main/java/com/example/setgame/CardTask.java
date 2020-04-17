package com.example.setgame;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CardTask extends AsyncTask<Request, Void, Response> {
    final SetViewActivity setViewActivity;
    int current_query;

    public CardTask(SetViewActivity activity) {
        this.setViewActivity = activity;

    }

    public Response requestToSetServer(Request req) {
        Gson gson = new Gson();
        // TODO: указывайте нужный порт
        String API_URL = "http://194.176.114.21:8059";
        try {
            URL url = new URL(API_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true); // setting POST method
            OutputStream out = urlConnection.getOutputStream();
            // сериализованный объект-запрос пишем в поток
            String outJSON = gson.toJson(req);
            Log.d("mytag", "out: "+outJSON);
            out.write(outJSON.getBytes());

            InputStream stream = urlConnection.getInputStream();
            Response response = gson.fromJson(new InputStreamReader(stream), Response.class);
            return response;

        } catch (IOException e) { return null; }

    }

    @Override
    protected Response doInBackground(Request... requests) {
        Request r = requests[0];
        return requestToSetServer(r);
    }

    @Override
    protected void onPostExecute(Response response) {

        if (current_query==1) {
            setViewActivity.fetchCards(response.cards);
        }
        else if (current_query==2){
            setViewActivity.cards_left = response.cards_left;
            setViewActivity.points = response.points;
            setViewActivity.changePoints();
            setViewActivity.checkCardsLeft();
        }

        }



}
