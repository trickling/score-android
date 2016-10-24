package com.example.android.scoresheet.app.Scorecards;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 10/21/2016.
 */
public class ScorecardsActivity extends AppCompatActivity implements ScorecardListFragment.Callback {
    private final String LOG_TAG = ScorecardsActivity.class.getSimpleName();
    private static final String SCORECARDVIEWFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scorecards);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (findViewById(R.id.scorecard_view_container) != null) {
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
                        .replace(R.id.scorecard_view_container, new ScorecardViewFragment(), SCORECARDVIEWFRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
            // Retrieve a reference to this activity's ActionBar.
            // Set the Z-axis elevation of the action bar in pixels.
        }

//        ScorecardListFragment scorecardListFragment =  ((ScorecardListFragment)getSupportFragmentManager()
//                .findFragmentById(R.id.fragment_list_scorecard));

        if (savedInstanceState == null) {
//         Create the detail fragment and add it to the activity
//         using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(ScorecardListFragment.SCORECARDLIST_URI, getIntent().getData());

            ScorecardListFragment fragment = new ScorecardListFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.scorecard_container, fragment)
                    .commit();
        }
    }

//    protected void onResume() {
//        // Resume and either load unchanged data or updated data
//        super.onResume();
//        ScorecardListFragment slf = (ScorecardListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_list_scorecard);
//        ScorecardViewFragment svf = ( ScorecardViewFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_view_scorecard);
//    }

    @Override
    public void onItemSelected(Uri contentUri) {

        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle args = new Bundle();
            args.putParcelable(ScorecardViewFragment.SCORECARDVIEW_URI, contentUri);

            ScorecardViewFragment fragment = new ScorecardViewFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.scorecard_view_container, fragment, SCORECARDVIEWFRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, ScorecardViewActivity.class)
                    .setData(contentUri);
            startActivity(intent);
        }
    }
}