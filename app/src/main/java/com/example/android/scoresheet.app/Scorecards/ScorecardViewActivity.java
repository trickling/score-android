package com.example.android.scoresheet.app.Scorecards;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 10/6/2016.
 */
public class ScorecardViewActivity extends AppCompatActivity{

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_scorecard);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setElevation(0f);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(ScorecardViewFragment.SCORECARDVIEW_URI, getIntent().getData());

            mUri = arguments.getParcelable(ScorecardViewFragment.SCORECARDVIEW_URI);

            ScorecardViewFragment fragment = new ScorecardViewFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.scorecard_view_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scorecard_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_edit:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intent = new Intent(this, ScorecardEditActivity.class)
                        .setData(mUri);
                startActivity(intent);
                return true;

//            case android.R.id.home:
////                NavUtils.navigateUpFromSameTask(this);
//                intent = new Intent(this, ScorecardViewActivity.class)
//                        .setData(mUri);
//                startActivity(intent);
//                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}