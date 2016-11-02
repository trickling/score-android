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

    private static final String[] SCORECARD_COLUMNS = {
            ScorecardEntry.TABLE_NAME + "." + ScorecardEntry._ID,
            ScorecardEntry.COLUMN_ELEMENT,
            ScorecardEntry.COLUMN_MAXTIME_M,
            ScorecardEntry.COLUMN_MAXTIME_S,
            ScorecardEntry.COLUMN_FINISH_CALL,
            ScorecardEntry.COLUMN_FALSE_ALERT_FRINGE,
            ScorecardEntry.COLUMN_TIMED_OUT,
            ScorecardEntry.COLUMN_DISMISSED,
            ScorecardEntry.COLUMN_EXCUSED,
            ScorecardEntry.COLUMN_ABSENT,
            ScorecardEntry.COLUMN_ELIMINATED_DURING_SEARCH,
            ScorecardEntry.COLUMN_OTHER_FAULTS_DESCR,
            ScorecardEntry.COLUMN_OTHER_FAULTS_COUNT,
            ScorecardEntry.COLUMN_COMMENTS,
            ScorecardEntry.COLUMN_TOTAL_TIME,
            ScorecardEntry.COLUMN_PRONOUNCED,
            ScorecardEntry.COLUMN_JUDGE_SIGNATURE,
            ScorecardEntry.COLUMN_SEARCH_AREA,
            ScorecardEntry.COLUMN_HIDES_MAX,
            ScorecardEntry.COLUMN_HIDES_FOUND,
            ScorecardEntry.COLUMN_HIDES_MISSED,
            ScorecardEntry.COLUMN_TOTAL_FAULTS,
            ScorecardEntry.COLUMN_MAXPOINT,
            ScorecardEntry.COLUMN_TOTAL_POINTS
    };
    static final int COL_SCORECARD_ID = 0;
    static final int COL_ELEMENT = 1;
    static final int COL_MAXTM = 2;
    static final int COL_MAXTS = 3;
    static final int COL_FINCALL = 4;
    static final int COL_FAF = 5;
    static final int COL_TIMEOUT = 6;
    static final int COL_DISMISSED = 7;
    static final int COL_EXCUSED = 8;
    static final int COL_ABSENT = 9;
    static final int COL_EDS = 10;
    static final int COL_OFD = 11;
    static final int COL_OFC = 12;
    static final int COL_COMMENTS = 13;
    static final int COL_TOTALT = 14;
    static final int COL_PRON = 15;
    static final int COL_JS = 16;
    static final int COL_SA = 17;
    static final int COL_HDMAX = 18;
    static final int COL_HDFOUND = 19;
    static final int COL_HDMISSED = 20;
    static final int COL_TOTALFLTS = 21;
    static final int COL_MAXPT = 22;
    static final int COL_TOTALPTS = 23;

    private static final String[] TALLY_COLUMNS = {
            TallyEntry.TABLE_NAME + "." + TallyEntry._ID,
            TallyEntry.COLUMN_TOTAL_TIME,
            TallyEntry.COLUMN_TOTAL_FAULTS,
            TallyEntry.COLUMN_TOTAL_POINTS,
            TallyEntry.COLUMN_TITLE,
            TallyEntry.COLUMN_QUALIFYING_SCORE,
            TallyEntry.COLUMN_QUALIFYING_SCORES
    };
    public static final int COL_TALLY_ID = 0;
    public static final int COL_TOTAL_TIME = 1;
    public static final int COL_TOTAL_FAULTS = 2;
    public static final int COL_TOTAL_POINTS = 3;
    public static final int COL_TITLE = 4;
    public static final int COL_QSCORE = 5;
    public static final int COL_QSCORES = 6;

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

        mEventNameView.setText(eventName + " Details");

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