package com.example.homeassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MoviesActivity extends AppCompatActivity {
    String MovieList = new String();
    ArrayList<String> popularity = new ArrayList<>();
    ArrayList<String> test = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_activity);

        Intent intent = getIntent();

        final Spinner spnMovC = findViewById(R.id.spinner);

        Intent i = getIntent();
        ArrayList<String> items_list = new ArrayList<String>();
        items_list = i.getStringArrayListExtra("titleArr");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, items_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnMovC.setAdapter(adapter);
    }
}

