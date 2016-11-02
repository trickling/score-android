package com.example.android.scoresheet.app.Events;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.scoresheet.app.Entrants.EntrantViewDetailActivity;
import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.ResultsActivity;
import com.example.android.scoresheet.app.Scorecards.ScorecardsActivity;
import com.example.android.scoresheet.app.Tallies.TallyViewActivity;

/**
 * Created by Kari Stromsland on 8/25/2016.
 */
public class EventEntrantDetailActivity extends AppCompatActivity implements EventEntrantDetailFragment.Callback {

    private final String LOG_TAG = EventEntrantDetailActivity.class.getSimpleName();
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_entrant_event);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (findViewById(R.id.event_view_detail_container) != null) {
            // android.support.v7.app.AppCompatActivity

            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.event_view_detail_container, new EventViewDetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
            // Retrieve a reference to this activity's ActionBar.
            // Set the Z-axis elevation of the action bar in pixels.
        }

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(EventEntrantDetailFragment.EVENTENTRANTDETAIL_URI, getIntent().getData());

            EventEntrantDetailFragment fragment = new EventEntrantDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.event_entrant_detail_container, fragment)
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

//    protected void onResume() {
        // Resume and either load unchanged data or updated data
//        super.onResume();

//        EventEntrantDetailFragment eedf = (EventEntrantDetailFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_detail_entrant_event);
//        EventViewDetailFragment evvdf = (EventViewDetailFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_detail_view_event);
//    }

    @Override
    public void onEventItemSelected(Uri contentUri) {
        // Callback from EventEntrantDetailFragment to implement data updates to EventViewDetailFragment
        Intent intent = new Intent(this, EventViewDetailActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }

    @Override
    public void onResultsItemSelected(Uri contentUri) {
        // Callback from EventEntrantDetailFragment to implement data updates to EventViewDetailFragment
        Intent intent = new Intent(this, ResultsActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }

    @Override
    public void onEntrantSelected(Uri contentUri) {
        // Callback from EventEntrantDetailFragment to implement data updates to EventViewDetailFragment
        Intent intent = new Intent(this, EntrantViewDetailActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }

    @Override
    public void onEntrantScorecardSelected(Uri contentUri){
        Intent intent = new Intent(this, ScorecardsActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }

    @Override
    public void onEntrantTallySelected(Uri contentUri){
        Intent intent = new Intent(this, TallyViewActivity.class)
                .setData(contentUri);

        startActivity(intent);
    }

//    @Override
//    public void onEntrantScorecardSelectedtoRun(Uri contentUri){
//        Intent intent = new Intent(this, ScorecardEditActivity.class)
//                .setData(contentUri);
//        startActivity(intent);
//    }

}