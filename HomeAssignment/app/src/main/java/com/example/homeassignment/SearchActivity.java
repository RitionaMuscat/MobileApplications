package com.example.homeassignment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> MovieList = new ArrayList<HashMap<String, String>>();

    public static ArrayList<String> titleArr = new ArrayList<String>();
    public static ArrayList<String> popularityArr = new ArrayList<String>();
    public static ArrayList<String> voteCountArr = new ArrayList<String>();
    public static ArrayList<String> videoArr = new ArrayList<String>();
    public static ArrayList<String> posterArr = new ArrayList<String>();
    public static ArrayList<String> idArr = new ArrayList<String>();
    public static ArrayList<String> adultArr = new ArrayList<String>();
    public static ArrayList<String> backdropArr = new ArrayList<String>();
    public static ArrayList<String> languageArr = new ArrayList<String>();
    public static ArrayList<String> origTitleArr = new ArrayList<String>();
    public static ArrayList<String> voteAvgArr = new ArrayList<String>();
    public static ArrayList<String> overViewArr = new ArrayList<String>();
    public static ArrayList<String> releaseDateArr = new ArrayList<String>();

    ListView listView;

    ArrayAdapter<String> arrayAdapter;
    EditText etSearch;

    HashMap<String, String> Movies = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getDisplayMetrics().widthPixels > getResources().getDisplayMetrics().
                heightPixels) {
            setContentView(R.layout.activity_search_land);
        } else {
            setContentView(R.layout.activity_search);

        }

        final ArrayList<String> arr = new ArrayList<>();
        new GetMovies().execute();

        listView = findViewById(R.id.listView);
        etSearch = findViewById(R.id.etSearch);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, titleArr);
        arrayAdapter.notifyDataSetChanged();
        listView.setAdapter(arrayAdapter);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent setIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(setIntent);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "portrait", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        menuItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_login:
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                return true;
            case R.id.action_movies:
                Intent i3 = new Intent(getApplicationContext(), MoviesActivity.class);
                startActivity(i3);
                return true;
            case R.id.action_Home:
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class GetMovies extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(SearchActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String ApiKey = "269f0b365935d94450397fa37668f42b";
            String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + ApiKey;

            HttpClient sh = new HttpClient();
            String jsonStr = sh.makeServiceCall(url);

            Log.e("TAG", "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray arrJson = jsonObject.getJSONArray("results");

                    for (int i = 0; i < arrJson.length(); i++) {
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

                        Movies.put("popularity", popularity);
                        Movies.put("vote_count", vote_count);
                        Movies.put("video", video);
                        Movies.put("poster_path", poster_path);
                        Movies.put("id", id);
                        Movies.put("adult", adult);
                        Movies.put("backdrop_path", backdrop_path);
                        Movies.put("original_language", original_language);
                        Movies.put("original_title", original_title);
                        Movies.put("title", title);
                        Movies.put("vote_average", vote_average);
                        Movies.put("overview", overview);
                        Movies.put("release_date", release_date);

                        MovieList.add(Movies);

                        titleArr.add(Movies.get("title"));
                        popularityArr.add(Movies.get("popularity"));
                        voteCountArr.add(Movies.get("vote_count"));
                        posterArr.add(Movies.get("poster_path"));
                        idArr.add(Movies.get("id"));
                        adultArr.add(Movies.get("adult"));
                        backdropArr.add(Movies.get("backdrop_path"));
                        languageArr.add(Movies.get("original_language"));
                        origTitleArr.add(Movies.get("original_title"));
                        voteAvgArr.add(Movies.get("vote_average"));
                        overViewArr.add(Movies.get("overview"));
                        releaseDateArr.add(Movies.get("release_date"));
                        videoArr.add(Movies.get("video"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


    }


}
