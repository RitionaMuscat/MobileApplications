package com.example.homeassignment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ArrayList<HashMap<String, String>> MovieList = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> Movies = new HashMap<>();
    public static  ArrayList<String> titleArr = new ArrayList<String>();
    public static   HashMap<String, String> MovieTitle = new HashMap<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        new GetMovies().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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

                        MovieTitle.put("title",title);
                        titleArr.add(MovieTitle.get("title"));
                        Log.e("TAG", "Response from MovieTitle in main: " + MovieTitle.get("title"));
                     Log.e("TAG", "Response from MovieTitle in main: " + titleArr);



                    }
                } catch ( JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        protected void onPostExecute (Void result)
        {
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            int id = menuItem.getItemId();
                            if (id == R.id.nav_popularMovies) {
                                 Intent MOVIE = new Intent(getApplicationContext(), MoviesActivity.class);
                                 MOVIE.putStringArrayListExtra("titleArr", titleArr);
                                 startActivity(MOVIE);

                            }
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            return true;
                        }
                    });

        }
    }

}
