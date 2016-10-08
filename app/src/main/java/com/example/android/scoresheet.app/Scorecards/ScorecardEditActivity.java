package com.example.android.scoresheet.app.Scorecards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 10/6/2016.
 */
public class ScorecardEditActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_scorecard);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setElevation(0f);

        if (savedInstanceState == null) {
//         Create the detail fragment and add it to the activity
//         using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(ScorecardEditFragment.SCORECARDEDIT_URI, getIntent().getData());

            ScorecardEditFragment fragment = new ScorecardEditFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.scorecard_edit_container, fragment)
                    .commit();
        }
    }
}