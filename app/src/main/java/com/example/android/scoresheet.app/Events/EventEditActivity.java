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

        Intent intent = new Intent(this, EventEditEntrantsActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }

    @Override
    public void onEventEditUsers(Uri contentUri) {

        Intent intent = new Intent(this, EventEditUsersActivity.class)
                .setData(contentUri);
        startActivity(intent);
    }
}
