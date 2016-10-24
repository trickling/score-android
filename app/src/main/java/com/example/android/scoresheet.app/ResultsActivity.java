package com.example.android.scoresheet.app;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Kari Stromsland on 10/10/2016.
 */
public class ResultsActivity extends AppCompatActivity{
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setElevation(0f);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(ResultsFragment.EVENTENTRANTTALLY_URI, getIntent().getData());

            mUri = arguments.getParcelable(ResultsFragment.EVENTENTRANTTALLY_URI);

            ResultsFragment fragment = new ResultsFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.results_container, fragment)
                    .commit();
        }
    }
}
