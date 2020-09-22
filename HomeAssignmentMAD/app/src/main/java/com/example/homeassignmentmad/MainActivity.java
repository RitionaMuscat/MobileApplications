package com.example.homeassignmentmad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> MovieList = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> Movies = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetMovies().execute();
    }
    public class GetMovies extends AsyncTask<Void,Void,Void> {

        protected  void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            String ApiKey = "269f0b365935d94450397fa37668f42b";
            String url = "https://api.themoviedb.org/3/movie/now_playing?api_key="+ApiKey;

            HttpClient sh = new HttpClient();
            String jsonStr = sh.makeServiceCall(url);

            Log.e("TAG", "Response from url: " + jsonStr);
            if(jsonStr != null)
            {
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray arrJson = jsonObject.getJSONArray("results");

                    for (int i = 0; i<arrJson.length();i++)
                    {
                        JSONObject c = arrJson.getJSONObject(i);
                        String popularity = c.getString("popularity");
                        String vote_count = c.getString("vote_count");
                        String video = c.getString("video");
                        String poster_path = c.getString("poster_path");
                        String id = c.getString("id");
                        String adult = c.getString("adult");
                        String backdrop_path = c.getString("backdrop_path");
                        String original_language = c.getString("original_language");
                        String original_title = c.getString("original_title");
                        String title = c.getString("title");
                        String vote_average = c.getString("vote_average");
                        String overview = c.getString("overview");
                        String release_date = c.getString("release_date");

                        Movies.put("popularity",popularity);
                        Movies.put("vote_count",vote_count);
                        Movies.put("video",video);
                        Movies.put("poster_path",poster_path);
                        Movies.put("id",id);
                        Movies.put("adult",adult);
                        Movies.put("backdrop_path",backdrop_path);
                        Movies.put("original_language",original_language);
                        Movies.put("original_title",original_title);
                        Movies.put("title",title);
                        Movies.put("vote_average",vote_average);
                        Movies.put("overview",overview);
                        Movies.put("release_date",release_date);

                        MovieList.add(Movies);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}