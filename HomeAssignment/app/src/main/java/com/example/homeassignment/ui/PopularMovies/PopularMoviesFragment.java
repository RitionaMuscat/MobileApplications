package com.example.homeassignment.ui.PopularMovies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.homeassignment.R;

import static com.example.homeassignment.R.id.text_Movies;

public class PopularMoviesFragment extends Fragment {

    private PopularMoviesViewModel PoplarMoviesModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PoplarMoviesModel =
                ViewModelProviders.of(this).get(PopularMoviesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_popular_movies, container, false);
        final TextView textView = root.findViewById(text_Movies);
        PoplarMoviesModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}