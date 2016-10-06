package com.example.android.scoresheet.app.Events;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetDbHelper;

/**
 * Created by Kari Stromsland on 8/25/2016.
 */
public class EventEntrantDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{

    private static final String LOG_TAG = EventEntrantDetailFragment.class.getSimpleName();
    private EventEntrantDetailAdapter mEventEntrantDetailAdapter;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private ScoreSheetDbHelper mOpenHelper;
    static final String EVENTENTRANTDETAIL_URI = "URI";
    private static final String EVENT_SHARE_HASHTAG = " #ScoreSheetApp";
    private Uri mUri;

    private TextView mDescriptionView;


//    private ShareActionProvider mShareActionProvider;
    private String mEventEntrant;

    private static final int EVENT_DETAIL_LOADER = 0;

    private static final String[] EVENT_DETAIL_COLUMNS = {
            EventEntry.TABLE_NAME + "." + EventEntry._ID,
            EventEntry.COLUMN_SHORT_DESC
    };
    public static final int COL_EVENT_ID = 0;
    public static final int COL_EVENT_DESC = 1;

    private static final String[] ENTRANT_DETAIL_COLUMNS = {
            EntrantEntry.TABLE_NAME + "." + EntrantEntry._ID,
            EntrantEntry.COLUMN_TEAM_DESC
    };
    public static final int COL_ENTRANT_ID = 0;
    public static final int COL_TEAM_DESC = 1;

    private static final String[] EVENTENTRANT_COLUMNS = {
            EventEntrantScorecardEntry.TABLE_NAME + "." + EventEntrantScorecardEntry._ID,
            EventEntrantScorecardEntry.COLUMN_EVENT_ID, EventEntrantScorecardEntry.COLUMN_ENTRANT_ID, EventEntrantScorecardEntry.COLUMN_SCORECARD_ID
    };

    public static final int COL_EVENTENTRANT_ID = 0;
    public static final int COL_EV_ID = 1;
    public static final int COL_EN_ID = 2;
    public static final int COL_SC_ID = 3;

    public interface Callback {
        /**
         * EventDetailFragmentCallback to EventEntrantDetailActivity for when an item has been selected.
         */
        public void onItemSelected(Uri evDescUri);
    }

    public EventEntrantDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add this line in order for this fragment to handle menu events.
//        setHasOptionsMenu(true);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        inflater.inflate(R.menu.event_entrant_detail_fragment, menu);

        // Retrieve the share menu item
//        MenuItem menuItem = menu.findItem(R.id.action_share);

        // Get the provider and hold onto it to set/change the share intent.
//        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        // If onLoadFinished happens before this, we can go ahead and set the share intent now.
//        if (mEvent != null) {
//            mShareActionProvider.setShareIntent(createShareEventIntent());
//        }
//    }

    //    private Intent createShareEventIntent() {
//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra(Intent.EXTRA_TEXT, mEvent + EVENT_SHARE_HASHTAG);
//        return shareIntent;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mEventEntrantDetailAdapter = new EventEntrantDetailAdapter(getActivity(), null, 0);

        View rootView = inflater.inflate(R.layout.fragment_detail_entrant_event, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EventEntrantDetailFragment.EVENTENTRANTDETAIL_URI);
        }

        mListView = (ListView) rootView.findViewById(R.id.listview_entrant_events);

        mListView.setAdapter(mEventEntrantDetailAdapter);

        mDescriptionView = (TextView) rootView.findViewById(R.id.event_summary_textview);

        mDescriptionView.setText(EventEntry.getEventIdDescriptionFromUri(mUri));

        View.OnClickListener DescOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUri != null) {
                    ((Callback) getActivity()).onItemSelected(mUri);
                }
            }
        };

        mDescriptionView.setOnClickListener(DescOnClickListener);


//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
//
//                if (cursor != null) {
//                    ((Callback) getActivity()).onItemSelected(ScoreSheetContract.EntrantEntry.buildEntrantDesc(cursor.getString(COL_TEAM_DESC)));
//                }
//                mPosition = position;  //  Lesson 5.12
//            }
//        });

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        return rootView;
    }

    // Lesson 5.10
//    void onLocationChanged( String newLocation ) {
//        // replace the uri, since the location has changed
//        Uri uri = mUri;
//        if (null != uri) {
//            long date = WeatherContract.WeatherEntry.getDateFromUri(uri);
//            Uri updatedUri = WeatherContract.WeatherEntry.buildWeatherLocationWithDate(newLocation, date);
//            mUri = updatedUri;
//            getLoaderManager().restartLoader(DETAIL_LOADER, null, this);
//        }
//    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(EVENT_DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // When tablets rotate, the currently selected list item needs to be saved.
        // When no item is selected, mPosition will be set to Listview.INVALID_POSITION,
        // so check for that before storing.
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }




    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        String sortOrder = EntrantEntry.COLUMN_TEAM_DESC + " ASC";

        Uri event_entrantUri = EntrantEntry.buildEntrantIdUri(EventEntry.getEventIdFromUri(mUri));

        return new CursorLoader(getActivity(), event_entrantUri, ENTRANT_DETAIL_COLUMNS, null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mEventEntrantDetailAdapter.swapCursor(data);

        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }


//        if (data != null && data.moveToFirst()) {
//
//            // Read description from cursor and update view
//            String description = data.getString(COL_EVENT_DESC);
//            mDescriptionView.setText(description);
//
//
//            // We still need this for the share intent
//            mEventEntrant = String.format("%s", description);

            // If onCreateOptionsMenu has already happened, we need to update the share intent now.
//            if (mShareActionProvider != null) {
//                mShareActionProvider.setShareIntent(createShareEventIntent());
//            }
//        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){  // Lesson 5.14
        mEventEntrantDetailAdapter.swapCursor(null);
    }

//    private void updateEvents(){
//        ScoreSheetSyncAdapter.syncImmediately(getActivity());
//    }
}
