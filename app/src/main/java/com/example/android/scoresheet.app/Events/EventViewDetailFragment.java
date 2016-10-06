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
import com.example.android.scoresheet.app.data.ScoreSheetContract;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetDbHelper;

/**
 * Created by Kari Stromsland on 9/9/2016.
 */
public class EventViewDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{

    private static final String LOG_TAG = EventViewDetailFragment.class.getSimpleName();
    private EventViewDetailAdapter mEventUserDetailAdapter;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private ScoreSheetDbHelper mOpenHelper;
    static final String EVENTUSERDETAIL_URI = "URI";
    private static final String EVENT_SHARE_HASHTAG = " #ScoreSheetApp";
    private Uri mUri;
    private TextView mDescrViewText;

    private static final int EVENT_USER_LOADER = 0;

    private static final String[] EVENT_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            ScoreSheetContract.EventEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntry._ID,
            ScoreSheetContract.EventEntry.COLUMN_SHORT_DESC
    };
    static final int COL_EVENT_ID = 0;
    static final int COL_EVENT_DESC = 1;

    private static final String[] USER_DETAIL_COLUMNS = {
            ScoreSheetContract.UserEntry.TABLE_NAME + "." + ScoreSheetContract.UserEntry._ID,
            ScoreSheetContract.UserEntry.COLUMN_USER_DESC
    };
    public static final int COL_USER_ID = 0;
    public static final int COL_USER_DESC = 1;

    private static final String[] EVENTUSER_COLUMNS = {
            ScoreSheetContract.EventUserEntry.TABLE_NAME + "." + ScoreSheetContract.EventUserEntry._ID,
            ScoreSheetContract.EventUserEntry.COLUMN_EVENT_ID, ScoreSheetContract.EventUserEntry.COLUMN_USER_ID
    };

    public static final int COL_EVENTUSER_ID = 0;
    public static final int COL_EV_ID = 1;
    public static final int COL_US_ID = 2;

    static final String EVENTVIEW_URI = "URI";

    public EventViewDetailFragment() {
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
//        inflater.inflate(R.menu.event_view_detail_fragment, menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
////        if (id == R.id.action_map) {
////            openPreferredLocationInMap();
////            return true;
////        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mEventUserDetailAdapter = new EventViewDetailAdapter(getActivity(), null, 0);

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_detail_view_event, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EventViewDetailFragment.EVENTVIEW_URI);
        }

        mListView = (ListView) rootView.findViewById(R.id.listview_user_events);

        mListView.setAdapter(mEventUserDetailAdapter);

        mDescrViewText = (TextView) rootView.findViewById(R.id.event_text_view);

        mDescrViewText.setText(EventEntry.getEventIdDescriptionFromUri(mUri));

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(EVENT_USER_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle){

        String sortOrder = ScoreSheetContract.UserEntry.COLUMN_USER_DESC + " ASC";

        Uri event_userUri = ScoreSheetContract.UserEntry.buildUserIdUri(EventEntry.getEventIdFromUri(mUri));

        return new CursorLoader(getActivity(), event_userUri, USER_DETAIL_COLUMNS, null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        mEventUserDetailAdapter.swapCursor(data);

        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){  // Lesson 5.14
        mEventUserDetailAdapter.swapCursor(null);
    }

//    private void updateEvents(){
//        ScoreSheetSyncAdapter.syncImmediately(getActivity());
//    }
}
