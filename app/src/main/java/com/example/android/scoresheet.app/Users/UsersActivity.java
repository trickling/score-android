package com.example.android.scoresheet.app.Users;

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
public class UsersActivity extends AppCompatActivity implements UserListFragment.Callback {
    private final String LOG_TAG = UsersActivity.class.getSimpleName();
    private static final String USERDETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_users);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if (findViewById(R.id.user_view_detail_container) != null) {
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
                        .replace(R.id.user_view_detail_container, new UserViewDetailFragment(), USERDETAILFRAGMENT_TAG)
                        .commit();
            }
        } else {
            mTwoPane = false;
//            getSupportActionBar().setElevation(0f);
            // Retrieve a reference to this activity's ActionBar.
            // Set the Z-axis elevation of the action bar in pixels.
        }

        UserListFragment userListFragment =  ((UserListFragment)getSupportFragmentManager()
                .findFragmentById(R.id.fragment_list_user));

//        eventFragment.setUseTodayLayout(!mTwoPane);
//        ScoreSheetSyncAdapter.initializeSyncAdapter(this);
    }
    protected void onResume() {
        // Resume and either load unchanged data or updated data
        super.onResume();
        UserListFragment enlf = (UserListFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_list_user);
        UserViewDetailFragment envdf = ( UserViewDetailFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_detail_view_user);
    }

    @Override
    public void onItemSelected(Uri contentUri) {
        // Callback from EventListFragment to implement data updates to EventUserDetailFragment

//        if (contentUri.getPathSegments().get(0).equals(UserEntry.TABLE_NAME)) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle args = new Bundle();
            args.putParcelable(UserViewDetailFragment.USERVIEW_URI, contentUri);

            UserViewDetailFragment fragment = new UserViewDetailFragment();
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.user_view_detail_container, fragment, USERDETAILFRAGMENT_TAG)
                    .commit();
        } else {
            Intent intent = new Intent(this, UserViewDetailActivity.class)
                    .setData(contentUri);
            startActivity(intent);
        }
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_users, menu);
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
                startActivity(new Intent(this, UserNewActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public void onUserEdit(Uri contentUri) {
        Intent intent = new Intent(this, UserEditActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }
}