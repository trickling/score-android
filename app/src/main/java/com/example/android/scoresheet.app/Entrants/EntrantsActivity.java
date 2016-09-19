package com.example.android.scoresheet.app.Entrants;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.scoresheet.app.OptionsActivity;
import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 9/17/2016.
 */
public class EntrantsActivity extends AppCompatActivity  implements EntrantListFragment.Callback{


    private final String LOG_TAG = EntrantsActivity.class.getSimpleName();
    private static final String ENTRANTDETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entrants);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if (findViewById(R.id.entrant_view_detail_container) != null) {
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
                        .replace(R.id.entrant_view_detail_container, new EntrantViewDetailFragment(), ENTRANTDETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
//            getSupportActionBar().setElevation(0f);
            // Retrieve a reference to this activity's ActionBar.
            // Set the Z-axis elevation of the action bar in pixels.
        }

        EntrantListFragment entrantListFragment =  ((EntrantListFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_list_entrant));

//        eventFragment.setUseTodayLayout(!mTwoPane);
//        ScoreSheetSyncAdapter.initializeSyncAdapter(this);
    }
    protected void onResume() {
        // Resume and either load unchanged data or updated data
        super.onResume();
        EntrantListFragment enlf = (EntrantListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_list_entrant);
        EntrantViewDetailFragment envdf = ( EntrantViewDetailFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_detail_view_entrant);
    }

    @Override
    public void onItemSelected(Uri contentUri) {
        // Callback from EventListFragment to implement data updates to EventEntrantDetailFragment

//        if (contentUri.getPathSegments().get(0).equals(EntrantEntry.TABLE_NAME)) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle args = new Bundle();
            args.putParcelable(EntrantViewDetailFragment.ENTRANTVIEW_URI, contentUri);

            EntrantViewDetailFragment fragment = new EntrantViewDetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.entrant_view_detail_container, fragment, ENTRANTDETAILFRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, EntrantViewDetailActivity.class)
                    .setData(contentUri);
            startActivity(intent);
        }
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entrants, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, OptionsActivity.class));
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_add:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                startActivity(new Intent(this, EntrantNewActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void onEntrantEdit(Uri contentUri) {
        Intent intent = new Intent(this, EntrantEditActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }

}
