package com.example.android.scoresheet.app.Scorecards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 10/24/2016.
 */
public class StopwatchActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        Bundle arguments = new Bundle();
        arguments.putParcelable(StopwatchFragment.STOPWATCH_URI, getIntent().getData());

        StopwatchFragment fragment = new StopwatchFragment();
        fragment.setArguments(arguments);
        // Lesson 5.10

        getSupportFragmentManager().beginTransaction()
                .add(R.id.stopwatch_container, fragment)
                .commit();
    }
}