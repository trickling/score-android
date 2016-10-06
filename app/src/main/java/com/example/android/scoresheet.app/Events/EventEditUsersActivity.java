package com.example.android.scoresheet.app.Events;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.android.scoresheet.app.R;

/**
 * Created by Kari Stromsland on 10/3/2016.
 */
public class EventEditUsersActivity extends AppCompatActivity{
    private boolean mTwoPane;
    private static final String EVENTEDITUSERSFRAGMENT_TAG = "DFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_users_edit_event);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setElevation(0f);

        if (savedInstanceState == null) {
//         Create the detail fragment and add it to the activity
//         using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(EventEditUsersFragment.EVENTEDITUSERS_URI, getIntent().getData());

            EventEditUsersFragment fragment = new EventEditUsersFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.event_edit_users_container, fragment)
                    .commit();
        }
    }

//    @Override
//    public void onItemSelected(Uri contentUri, Boolean checked) {
//        // Callback from EventEventEditEntrantsFragment
//
////        if (contentUri.getPathSegments().get(0).equals(EntrantEntry.TABLE_NAME)) {
//        if (mTwoPane) {
//            // In two-pane mode, show the detail view in this activity by
//            // adding or replacing the detail fragment using a
//            // fragment transaction.
//            Bundle args = new Bundle();
//            args.putParcelable(EventEditEntrantsFragment.EVENTEDITENTRANTS_URI, contentUri);
//
//            EventEntrantDetailFragment fragment = new EventEntrantDetailFragment();
//            fragment.setArguments(args);
//
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.event_edit_entrants_container, fragment, EVENTEDITENTRANTSFRAGMENT_TAG)
//                    .commit();
//        } else {
//            if (checked){
//                // add entrant_id
//            }else if (!checked){
//                // delete entrant_id
//            }
//        }
//    }
}