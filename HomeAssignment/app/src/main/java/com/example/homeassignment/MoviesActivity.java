package com.example.homeassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MoviesActivity extends AppCompatActivity {
    String selectedMovie = "";
    Integer movieIndex = null;

    ArrayList<String> MoviesNames = new ArrayList<String>();
    ArrayList<String> popularityArr = new ArrayList<String>();
    ArrayList<String> voteCountArr = new ArrayList<String>();
    ArrayList<String> videoArr = new ArrayList<String>();
    ArrayList<String> posterArr = new ArrayList<String>();
    ArrayList<String> idArr = new ArrayList<String>();
    ArrayList<String> adultArr = new ArrayList<String>();
    ArrayList<String> backdropArr = new ArrayList<String>();
    ArrayList<String> languageArr = new ArrayList<String>();
    ArrayList<String> origTitleArr = new ArrayList<String>();
    ArrayList<String> voteAvgArr = new ArrayList<String>();
    ArrayList<String> overViewArr = new ArrayList<String>();
    ArrayList<String> releaseDateArr = new ArrayList<String>();
    ArrayList<HashMap<String, String>> FavouritesArr = new ArrayList<HashMap<String, String>>();

    HashMap<String, String> Movies = new HashMap<>();

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("favouriteMovie");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_activity);

        Intent intent = getIntent();

        final Spinner spnMovC = findViewById(R.id.spinner);

        Intent i = getIntent();

        MoviesNames = i.getStringArrayListExtra("titleArr");
        popularityArr = i.getStringArrayListExtra("popularityArr");
        voteCountArr = i.getStringArrayListExtra("voteCountArr");
        videoArr = i.getStringArrayListExtra("videoArr");
        posterArr = i.getStringArrayListExtra("posterArr");
        idArr = i.getStringArrayListExtra("idArr");
        adultArr = i.getStringArrayListExtra("adultArr");
        backdropArr = i.getStringArrayListExtra("backdropArr");
        languageArr = i.getStringArrayListExtra("languageArr");
        origTitleArr = i.getStringArrayListExtra("origTitleArr");
        voteAvgArr = i.getStringArrayListExtra("voteAvgArr");
        overViewArr = i.getStringArrayListExtra("overViewArr");
        releaseDateArr = i.getStringArrayListExtra("releaseDateArr");

        Log.e("TAG", "Response from popularityArr: " + popularityArr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, MoviesNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnMovC.setAdapter(adapter);

        spnMovC.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMovie = spnMovC.getSelectedItem().toString();
                movieIndex = MoviesNames.indexOf(selectedMovie);
                if (MoviesNames.contains(selectedMovie)) {
                    TextView Popularity;
                    Popularity = findViewById(R.id.popularity);
                    Popularity.setText("Popularity: " + popularityArr.get(movieIndex));

                    TextView adult;
                    adult = findViewById(R.id.adult);
                    adult.setText("Adult: " + adultArr.get(movieIndex));

                    TextView vote;
                    vote = findViewById(R.id.vote);
                    vote.setText("Average Vote: " + voteAvgArr.get(movieIndex));

                    TextView voteC;
                    voteC = findViewById(R.id.voteC);
                    voteC.setText("Vote: " + voteCountArr.get(movieIndex));

                    TextView overView;
                    overView = findViewById(R.id.OverView);
                    overView.setText("Overview: " + overViewArr.get(movieIndex));

                    TextView backdrop;
                    backdrop = findViewById(R.id.backdrop);
                    backdrop.setText("Back drop: " + backdropArr.get(movieIndex));

                    TextView language;
                    language = findViewById(R.id.language);
                    language.setText("Language: " + languageArr.get(movieIndex));

                    TextView origTitle;
                    origTitle = findViewById(R.id.origTitle);
                    origTitle.setText("Original Title: " + origTitleArr.get(movieIndex));

                    TextView video;
                    video = findViewById(R.id.video);
                    video.setText("Video: " + videoArr.get(movieIndex));

                    TextView poster;
                    poster = findViewById(R.id.poster);
                    poster.setText("Poster: " + posterArr.get(movieIndex));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnFavourite;
        btnFavourite = findViewById(R.id.favouritesBTN);
        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Movies.put("popularity", popularityArr.get(movieIndex));
                Movies.put("vote_count", voteCountArr.get(movieIndex));
                Movies.put("video", videoArr.get(movieIndex));
                Movies.put("poster_path", posterArr.get(movieIndex));
                Movies.put("adult", adultArr.get(movieIndex));
                Movies.put("backdrop_path", backdropArr.get(movieIndex));
                Movies.put("original_language", languageArr.get(movieIndex));
                Movies.put("original_title", origTitleArr.get(movieIndex));
                Movies.put("title", selectedMovie);
                Movies.put("vote_average", voteAvgArr.get(movieIndex));
                Movies.put("overview", overViewArr.get(movieIndex));
                Movies.put("release_date", releaseDateArr.get(movieIndex));

                FavouritesArr.add(Movies);

                Log.e("TAG", "Response from popularityArr: " + FavouritesArr);
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_movies);
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
            case R.id.action_Home:
                Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
}

