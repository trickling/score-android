package com.example.android.scoresheet.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.scoresheet.app.sync.ScoreSheetSyncAdapter;

public class MainActivity extends AppCompatActivity implements EventListFragment.Callback {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;
//    private String mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (findViewById(R.id.event_entrant_detail_container) != null) {
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
                        .replace(R.id.event_entrant_detail_container, new EventEntrantDetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
//            getSupportActionBar().setElevation(0f);
            // Retrieve a reference to this activity's ActionBar.
            // Set the Z-axis elevation of the action bar in pixels.
        }
        EventListFragment eventListFragment =  ((EventListFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_list_event));

//        eventFragment.setUseTodayLayout(!mTwoPane);
        ScoreSheetSyncAdapter.initializeSyncAdapter(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

            case R.id.nav_actions:
                startActivity(new Intent(this, NavActivity.class));
                return true;

            case R.id.action_add:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                startActivity(new Intent(this, EventNewActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    protected void onResume() {
        // Resume and either load unchanged data or updated data
        super.onResume();
        EventListFragment elf = (EventListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_list_event);
        EventEntrantDetailFragment eedf = (EventEntrantDetailFragment)getSupportFragmentManager().findFragmentByTag(DETAILFRAGMENT_TAG);
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
            args.putParcelable(EventEntrantDetailFragment.EVENTENTRANTDETAIL_URI, contentUri);

            EventEntrantDetailFragment fragment = new EventEntrantDetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.event_entrant_detail_container, fragment, DETAILFRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, EventEntrantDetailActivity.class)
                    .setData(contentUri);
            startActivity(intent);
        }
//        }
    }

    public void onEventEdit(Uri contentUri) {
        Intent intent = new Intent(this, EventEditActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }
}