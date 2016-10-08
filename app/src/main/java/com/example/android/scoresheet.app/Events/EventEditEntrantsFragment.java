package com.example.android.scoresheet.app.Events;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;

/**
 * Created by Kari Stromsland on 9/19/2016.
 */
public class EventEditEntrantsFragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{
    // A loader is a class that performs asynchronous loading of data.
    // LoaderManager is an interface for managing one or more Loader instances associated with it.
    // LoaderManager.LoaderCallbacks <D> is a callback interface for a client to interact with the manager, D data.

    public static final String LOG_TAG = EventEditEntrantsFragment.class.getSimpleName();
    private EventEditEntrantsListAdapter mEventEntrantListAdapter;
    private ListView mListView;
    private Uri mUri;
    private static Uri eventUri;
    private long eventid;
    private long entrantid;
    private long scorecardid;
    private Boolean event_edit_checked;
    static final String EVENTEDITENTRANTS_URI = "URI";
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";

    private static final int EVENTENTRANTS_LOADER = 0;

    private static final String[] ENTRANT_COLUMNS = {
            EntrantEntry.TABLE_NAME + "." + EntrantEntry._ID,
            EntrantEntry.COLUMN_TEAM_DESC
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_ENTRANT_DESC = 1;

    private static final String[] EVENT_COLUMNS = {
            EventEntry.TABLE_NAME + "." + EventEntry._ID,
            EventEntry.COLUMN_SHORT_DESC
    };
    public static final int COL_EVENT_ID = 0;
    public static final int COL_EVENT_DESC = 1;

    private static final String[] EVENTENTRANT_COLUMNS = {
            EventEntrantScorecardEntry.TABLE_NAME + "." + EventEntrantScorecardEntry._ID,
            EventEntrantScorecardEntry.COLUMN_EVENT_ID, EventEntrantScorecardEntry.COLUMN_ENTRANT_ID
    };
    public static final int COL_EVENTENTRANT_ID = 0;
    public static final int COL_EV_ID = 1;
    public static final int COL_EN_ID = 2;
    public static final int COL_SC_ID = 3;

    public EventEditEntrantsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // ListAdapter for a ListView which derives AbsListView which derives from AdapterView.
        // AbsListView includes a RecyclerListener interface
        // The CursorAdapter binds db data to a child view of the listview. An adapter object acts as a bridge
        // between an AdapterView and the underlying data for that view.  The adapter provides access to the
        // data items.  The adapter is also responsible for making a view for each item in the data set.
        mEventEntrantListAdapter = new EventEditEntrantsListAdapter(getActivity(), null, 0);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_entrants_edit_event, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            eventUri = arguments.getParcelable(EventEditEntrantsFragment.EVENTEDITENTRANTS_URI);
        }

        mListView = (ListView) rootView.findViewById(R.id.listview_event_edit_entrants);

        registerForContextMenu(mListView);

        mListView.setAdapter(mEventEntrantListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // OnClick if false, get unchecked entrant_id with ""  if true, update entrant_id with checkmarked entrant_id
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                CheckedTextView tt = (CheckedTextView) view.findViewById(R.id.list_item_event_edit_entrants_textview);
                if (!tt.isChecked()){
                    mListView.setItemChecked(position, true);
                    tt.setChecked(true);
                    tt.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
                    event_edit_checked = true;
                }else{
                    mListView.setItemChecked(position, false);
                    tt.setChecked(false);
                    tt.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
                    event_edit_checked = false;
                }
                if (cursor != null) {
                    mUri = EntrantEntry.buildEntrantIdUri(cursor.getLong(COL_ENTRANT_ID));
                    entrantid = EntrantEntry.getEntrantIdFromUri(mUri);
                    eventid = EventEntry.getEventIdFromUri(eventUri);
                    if (event_edit_checked) {
                        ContentValues mSCContentValues = new ContentValues();
                        String scorecardDesc = eventid + " " + entrantid;
                        mSCContentValues.put(ScorecardEntry.COLUMN_SCORECARD_DESC, scorecardDesc);
                        Uri scUri = getContext().getContentResolver().insert(ScorecardEntry.buildScorecardUri(), mSCContentValues);
                        scorecardid = ScorecardEntry.getScorecardIdFromUri(scUri);
                        ContentValues mEditContentValues = new ContentValues();
                        mEditContentValues.put(EventEntrantScorecardEntry.COLUMN_ENTRANT_ID, entrantid);
                        mEditContentValues.put(EventEntrantScorecardEntry.COLUMN_EVENT_ID, eventid);
                        mEditContentValues.put(EventEntrantScorecardEntry.COLUMN_SCORECARD_ID, scorecardid);
                        Uri uri = EventEntrantScorecardEntry.buildEventEntrantScorecardUri();
                        getContext().getContentResolver().insert(EventEntrantScorecardEntry.buildEventEntrantScorecardUri(), mEditContentValues);
                    }else{
                        String sortOrder = EventEntrantScorecardEntry._ID + " ASC";
                        String selection = EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ?" + " AND " + EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ?";
                        Uri uri = EventEntrantScorecardEntry.buildIdEventEntrantScorecard(Long.valueOf(eventid).toString(), Long.valueOf(entrantid).toString());
                        String[] selectionArgs = {Long.valueOf(entrantid).toString(), Long.valueOf(eventid).toString()};
                        Cursor c = getContext().getContentResolver().query(uri, EVENTENTRANT_COLUMNS, selection, selectionArgs, sortOrder);
                        if(c.moveToFirst()) {
                            long scorecard_id = c.getLong(COL_SC_ID);
                            long evententrant_id = c.getLong(COL_EVENTENTRANT_ID);
                            String scSelection = ScorecardEntry._ID + " = ?";
                            String[] scSelectionArgs = {Long.valueOf(scorecard_id).toString()};
                            String dSelection = EventEntrantScorecardEntry._ID + " = ?";
                            String[] dSelectionArgs = {Long.valueOf(evententrant_id).toString()};
                            getContext().getContentResolver().delete(ScorecardEntry.CONTENT_URI, scSelection, scSelectionArgs);
                            getContext().getContentResolver().delete(EventEntrantScorecardEntry.CONTENT_URI, dSelection, dSelectionArgs);
                        }
                    }
                }
                mPosition = position;
            }
        });

        mListView.setOnLongClickListener(new View.OnLongClickListener() {
            // Called when the user long-clicks on someView

            public boolean onLongClick(View view) {

                view.setSelected(true);
                return true;
            }
        });


        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        return rootView;

    }

    public static boolean checked_status(Context context, Cursor c){
        boolean checked = false;
        long entrantid = c.getLong(COL_ENTRANT_ID);
        long eventid = ScoreSheetContract.EventEntry.getEventIdFromUri(eventUri);
        Uri uri = EntrantEntry.buildEntrantEventIdCheckedUri(eventid, "checked");
        Cursor cursor;
        cursor = context.getContentResolver().query(uri, ENTRANT_COLUMNS, null, null, null);
        if (!cursor.moveToFirst()) {
            checked = false;
        }else {
            do {
                if(cursor.getLong(0) == entrantid) {
                    checked = true;
                }
            }while(cursor.moveToNext());
        }
        return checked;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(EVENTENTRANTS_LOADER, null, this);
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
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle){

        String sortOrder = EntrantEntry.COLUMN_TEAM_DESC + " ASC";

        // CursorLoader is a loader that queries the ContentResolver and returns a Cursor.  This class implements
        // the Loader protocol in a standard way for querying cursors, building on AsyncTaskLoader to perform
        // the cursor query on a background thread.
//        return new CursorLoader(getActivity(), EntrantEntry.CONTENT_URI, ENTRANT_COLUMNS, null, null, sortOrder);

        return new CursorLoader(getActivity(), EntrantEntry.CONTENT_URI, ENTRANT_COLUMNS, null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        mEventEntrantListAdapter.swapCursor(data);
        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){  // Lesson 5.14
        mEventEntrantListAdapter.swapCursor(null);
    }
}