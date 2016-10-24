package com.example.android.scoresheet.app.Events;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.scoresheet.app.Entrants.EntrantListAdapter;
import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantTallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetDbHelper;

/**
 * Created by Kari Stromsland on 8/25/2016.
 */
public class EventEntrantDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{

    private static final String LOG_TAG = EventEntrantDetailFragment.class.getSimpleName();

    private EntrantListAdapter mEventEntrantDetailAdapter;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private ScoreSheetDbHelper mOpenHelper;
    static final String EVENTENTRANTDETAIL_URI = "URI";
    private static final String EVENT_SHARE_HASHTAG = " #ScoreSheetApp";
    private Uri mUri;
    private Uri resultsUri;

    private TextView mEventNameView;
    private TextView mResultsDescriptionView;

    private static final int EVENT_DETAIL_LOADER = 0;

//    private static final String[] EVENT_DETAIL_COLUMNS = {
//            EventEntry.TABLE_NAME + "." + EventEntry._ID,
//            EventEntry.COLUMN_NAME
//    };
//    public static final int COL_EVENT_ID = 0;
//    public static final int COL_NAME = 1;

    private static final String[] ENTRANT_DETAIL_COLUMNS = {
            EntrantEntry.TABLE_NAME + "." + EntrantEntry._ID,
            EntrantEntry.COLUMN_FIRST_NAME,
            EntrantEntry.COLUMN_LAST_NAME,
            EntrantEntry.COLUMN_ID_NUMBER,
            EntrantEntry.COLUMN_DOG_NAME,
            EntrantEntry.COLUMN_DOG_ID_NUMBER,
            EntrantEntry.COLUMN_BREED
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_FIRST_NAME = 1;
    static final int COL_LAST_NAME = 2;
    static final int COL_ID = 3;
    static final int COL_DOG_NAME = 4;
    static final int COL_DOG_ID = 5;
    static final int COL_BREED = 6;

    private static final String[] EVENTENTRANTSCORECARD_COLUMNS = {
            EventEntrantScorecardEntry.TABLE_NAME + "." + EventEntrantScorecardEntry._ID,
            EventEntrantScorecardEntry.COLUMN_EVENT_ID, EventEntrantScorecardEntry.COLUMN_ENTRANT_ID, EventEntrantScorecardEntry.COLUMN_SCORECARD_ID
    };
    public static final int COL_EVENTENTRANTSCORECARD_ID = 0;
    public static final int COL_EVSC_ID = 1;
    public static final int COL_ENSC_ID = 2;
    public static final int COL_SC_ID = 3;


    private static final String[] EVENTENTRANTTALLY_COLUMNS = {
            EventEntrantTallyEntry.TABLE_NAME + "." + EventEntrantTallyEntry._ID,
            EventEntrantTallyEntry.COLUMN_EVENT_ID, EventEntrantTallyEntry.COLUMN_ENTRANT_ID, EventEntrantTallyEntry.COLUMN_TALLY_ID
    };
    public static final int COL_EVENTENTRANTTALLY_ID = 0;
    public static final int COL_EVTLY_ID = 1;
    public static final int COL_ENTLY_ID = 2;
    public static final int COL_TLY_ID = 3;

    public interface Callback {

        public void onEventItemSelected(Uri evNameUri);
        public void onResultsItemSelected(Uri resultsDescUri);
        public void onEntrantSelected(Uri enFirstNameUri);
        public void onEntrantScorecardSelected(Uri scElementUri);
        public void onEntrantTallySelected(Uri tlyTitleUri);
    }

    public EventEntrantDetailFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(getContext());
        inflater.inflate(R.menu.event_entrant_detail_fragment, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        String entrantid = Long.valueOf(mEventEntrantDetailAdapter.getCursor().getLong(COL_ENTRANT_ID)).toString();
        String eventid = Long.valueOf(EventEntry.getEventIdFromUri(mUri)).toString();

        String sortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String selection = EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ?" + " AND " + EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ?";
        String[] selectionArgs = {entrantid, eventid};
        String scorecardid;
        Cursor c = getContext().getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, selection, selectionArgs, sortOrder);
        if(c.moveToFirst()){
            scorecardid = Long.valueOf(c.getLong(COL_SC_ID)).toString();
        }else{
            scorecardid = "not found";
        }
        c.close();
        String tlySortOrder = EventEntrantTallyEntry._ID + " ASC";
        String tlySelection = EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " = ?" + " AND " + EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
        String[] tlySelectionArgs = {entrantid, eventid};
        String tallyid;
        Cursor cTly = getContext().getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, tlySelection, tlySelectionArgs, tlySortOrder);
        if(cTly.moveToFirst()){
            tallyid = Long.valueOf(cTly.getLong(COL_TLY_ID)).toString();
        }else{
            tallyid = "not found";
        }
        cTly.close();
        Uri ScUri = ScorecardEntry.buildScorecardIdUri(Long.parseLong(scorecardid));
        Uri EnUri = EntrantEntry.buildEntrantIdUri(Long.parseLong(entrantid));
        Uri TlyUri = TallyEntry.buildTallyIdUri(Long.parseLong(tallyid));
        Uri EvEnUri = ScorecardEntry.buildScorecardEventIdEntrantIdUri(eventid, entrantid);

        switch (item.getItemId()) {
            case R.id.team:
                viewTeamDetail(EnUri, info.id);
                return true;
            case R.id.scorecard:
                // TODO: needs to go to a list of scorecards for event entrant, user will select scorecard from there to view or run
                viewScorecard(EvEnUri, info.id);
                return true;
            case R.id.view_tally:
                viewTally(TlyUri, info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mEventEntrantDetailAdapter = new EntrantListAdapter(getActivity(), null, 0);

        View rootView = inflater.inflate(R.layout.fragment_detail_entrant_event, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EventEntrantDetailFragment.EVENTENTRANTDETAIL_URI);
        }

        mListView = (ListView) rootView.findViewById(R.id.listview_entrant_events);

        mListView.setAdapter(mEventEntrantDetailAdapter);

        registerForContextMenu(mListView);

        mEventNameView = (TextView) rootView.findViewById(R.id.event_name_textview);

        String eventName = EventEntry.getEventIdNameFromUri(mUri);

        mEventNameView.setText(eventName);

        String eventid = Long.valueOf(EventEntry.getEventIdFromUri(mUri)).toString();

        mResultsDescriptionView = (TextView) rootView.findViewById(R.id.event_results_textview);

        String sortOrder = EventEntrantTallyEntry._ID + " ASC";
        String selection = EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
        String[] selectionArgs = {eventid};
        Cursor tlyc = getContext().getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, selection, selectionArgs, sortOrder);
        String resultsDescr;
        long resultsid;
        if (tlyc.moveToFirst()) {
            resultsDescr = tlyc.getString(COL_EVTLY_ID);
            resultsid = tlyc.getLong(COL_ENTLY_ID);
        } else {
            resultsDescr = "not found";
            resultsid = 0;
        }

        resultsUri = EventEntrantTallyEntry.buildEventEntrantTallyIdUri(eventid);

        mResultsDescriptionView.setText(resultsDescr);

        mResultsDescriptionView = (TextView) rootView.findViewById(R.id.event_results_textview);

        mResultsDescriptionView.setText(eventName + " Results");

        View.OnClickListener EventNameOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUri != null) {
                    ((Callback) getActivity()).onEventItemSelected(mUri);
                }
            }
        };
        mEventNameView.setOnClickListener(EventNameOnClickListener);


        View.OnClickListener ResultsDescOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUri != null) {
                    ((Callback) getActivity()).onResultsItemSelected(resultsUri);
                }
            }
        };
        mResultsDescriptionView.setOnClickListener(ResultsDescOnClickListener);



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);

                if (cursor != null) {
                    ((Callback) getActivity()).onEntrantSelected(EntrantEntry.buildEntrantIdUri(cursor.getLong(COL_ENTRANT_ID)));
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

        String sortOrder = EntrantEntry.COLUMN_FIRST_NAME + " ASC";

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
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mEventEntrantDetailAdapter.swapCursor(null);
    }


    private void viewTeamDetail(Uri itemUri, long l){
        ((Callback) getActivity()).onEntrantSelected(itemUri);
    }

    private void viewScorecard(Uri itemUri, long l){
        ((Callback) getActivity()).onEntrantScorecardSelected(itemUri);
    }

    private void viewTally(Uri itemUri, long l){
        ((Callback) getActivity()).onEntrantTallySelected(itemUri);
    }
}