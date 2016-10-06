package com.example.android.scoresheet.app.Events;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 9/9/2016.
 */
public class EventEditActivity extends AppCompatActivity implements EventEditFragment.Callback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_event);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setElevation(0f);

        if (savedInstanceState == null) {
//         Create the detail fragment and add it to the activity
//         using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(EventEditFragment.EVENTEDIT_URI, getIntent().getData());

            EventEditFragment fragment = new EventEditFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.event_edit_container, fragment)
                    .commit();
        }
    }

    protected void onResume() {
        // Resume and either load unchanged data or updated data
        super.onResume();

        EventEditFragment edf = (EventEditFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_edit_event);
        EventEditEntrantsFragment eeef = (EventEditEntrantsFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_entrants_edit_event);
        EventEditUsersFragment eevf = (EventEditUsersFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_users_edit_event);
    }


    @Override
    public void onEventEditEntrants(Uri contentUri) {
        // Callback from EventEntrantDetailFragment to implement data updates to EventViewDetailFragment
        Intent intent = new Intent(this, EventEditEntrantsActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }

    @Override
    public void onEventEditUsers(Uri contentUri) {
        // Callback from EventEntrantDetailFragment to implement data updates to EventViewDetailFragment
        Intent intent = new Intent(this, EventEditUsersActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.event_new, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, OptionsActivity.class));
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    public Intent getParentActivityIntent() {
//        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//    }
}
