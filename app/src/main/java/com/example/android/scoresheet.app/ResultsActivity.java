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

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setElevation(0f);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(ResultsFragment.RESULTS_URI, getIntent().getData());

            mUri = arguments.getParcelable(ResultsFragment.RESULTS_URI);

            ResultsFragment fragment = new ResultsFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.results_container, fragment)
                    .commit();
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
