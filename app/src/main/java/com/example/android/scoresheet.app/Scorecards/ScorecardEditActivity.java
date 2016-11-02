package com.example.android.scoresheet.app.Scorecards;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 10/6/2016.
 */
public class ScorecardEditActivity extends AppCompatActivity implements StopwatchFragment.Callback{

    private static final String STOPWATCHFRAGMENT_TAG = "SFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_scorecard);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setElevation(0f);

        Bundle arguments = new Bundle();

        if (findViewById(R.id.stopwatch_container) != null) {
//         Create the detail fragment and add it to the activity
//         using a fragment transaction.

            if (savedInstanceState == null) {

                arguments.putParcelable(StopwatchFragment.STOPWATCH_URI, getIntent().getData());

                StopwatchFragment swfragment = new StopwatchFragment();
                swfragment.setArguments(arguments);

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.scorecard_edit_container, new StopwatchFragment(), STOPWATCHFRAGMENT_TAG)
                        .commit();
            }

        }

//        Bundle arguments = new Bundle();

        arguments.putParcelable(StopwatchFragment.STOPWATCH_URI, getIntent().getData());

        StopwatchFragment swfragment = new StopwatchFragment();
        swfragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.scorecard_edit_container, swfragment)
                .commit();


        arguments.putParcelable(ScorecardEditFragment.SCORECARDEDIT_URI, getIntent().getData());

        ScorecardEditFragment fragment = new ScorecardEditFragment();
        fragment.setArguments(arguments);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.scorecard_edit_container, fragment)
                .commit();
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

    @Override
    public void onTimeSave(Uri contentUri){
        Intent intent = new Intent(this, ScorecardEditActivity.class)
                .setData(contentUri);

        startActivity(intent);
    }
}