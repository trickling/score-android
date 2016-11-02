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

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantTallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventUserEntry;

/**
 * Created by Kari Stromsland on 9/6/2016.
 */
public class EventListFragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{
    public static final String LOG_TAG = EventListFragment.class.getSimpleName();
    private EventListAdapter mEventListAdapter;
    private ListView mListView;
    private Uri mUri;
    private String DescrText = new String("");
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";

    private static final int EVENT_LOADER = 0;

    private static final String[] EVENT_COLUMNS = {
            ScoreSheetContract.EventEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntry._ID,
            EventEntry.COLUMN_NAME,
            EventEntry.COLUMN_LOCATION,
            EventEntry.COLUMN_DATE,
            EventEntry.COLUMN_HOST,
            EventEntry.COLUMN_STATUS,
            EventEntry.COLUMN_DIVISION,
            EventEntry.COLUMN_INT_SEARCH_AREAS,
            EventEntry.COLUMN_EXT_SEARCH_AREAS,
            EventEntry.COLUMN_CONT_SEARCH_AREAS,
            EventEntry.COLUMN_VEH_SEARCH_AREAS,
            EventEntry.COLUMN_ELITE_SEARCH_AREAS,
            EventEntry.COLUMN_INT_HIDES,
            EventEntry.COLUMN_EXT_HIDES,
            EventEntry.COLUMN_CONT_HIDES,
            EventEntry.COLUMN_VEH_HIDES,
            EventEntry.COLUMN_ELITE_HIDES
    };
    public static final int COL_EVENT_ID = 0;
    public static final int COL_NAME = 1;
    public static final int COL_LOC = 2;
    public static final int COL_DATE = 3;
    public static final int COL_HOST = 4;
    public static final int COL_STATUS = 5;
    public static final int COL_DIV = 6;
    public static final int COL_INT_SA = 7;
    public static final int COL_EXT_SA = 8;
    public static final int COL_CONT_SA = 9;
    public static final int COL_VEH_SA = 10;
    public static final int COL_ELITE_SA = 11;
    public static final int COL_INT_HD = 12;
    public static final int COL_EXT_HD = 13;
    public static final int COL_CONT_HD = 14;
    public static final int COL_VEH_HD = 15;
    public static final int COL_ELITE_HD = 16;

    private static final String[] EVENTENTRANTSCORECARD_COLUMNS = {
            ScoreSheetContract.EventEntrantScorecardEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantScorecardEntry._ID,
            ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_EVENT_ID, ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_ENTRANT_ID, ScoreSheetContract.EventEntrantScorecardEntry.COLUMN_SCORECARD_ID
    };
    public static final int COL_EVENTENTRANTSCORECARD_ID = 0;
    public static final int COL_EVSC_ID = 1;
    public static final int COL_ENSC_ID = 2;
    public static final int COL_SC_ID = 3;

    private static final String[] EVENTENTRANTTALLY_COLUMNS = {
            ScoreSheetContract.EventEntrantTallyEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntrantTallyEntry._ID,
            ScoreSheetContract.EventEntrantTallyEntry.COLUMN_EVENT_ID, ScoreSheetContract.EventEntrantTallyEntry.COLUMN_ENTRANT_ID, ScoreSheetContract.EventEntrantTallyEntry.COLUMN_TALLY_ID
    };
    public static final int COL_EVENTENTRANTTALLY_ID = 0;
    public static final int COL_EVTLY_ID = 1;
    public static final int COL_ENTLY_ID = 2;
    public static final int COL_TLY_ID = 3;

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


    public interface Callback {

        public void onItemSelected(Uri descUri);
        public void onEventEdit(Uri editUri);
    }

    public EventListFragment() {
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
        inflater.inflate(R.menu.event_list_fragment, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        mUri = EventEntry.buildEventIdNameUri(mEventListAdapter.getCursor().getLong(COL_EVENT_ID), mEventListAdapter.getCursor().getString(COL_NAME));
        switch (item.getItemId()) {
            case R.id.edit:
                editEvent(mUri, info.id);
                return true;
            case R.id.delete:
                deleteEvent(mUri, info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteEvent(Uri itemUri, long l){

        String evenscSortOrder = EventEntrantScorecardEntry._ID + " ASC";
        String eventlySortOrder = EventEntrantTallyEntry._ID + " ASC";
        String evselection = EventEntry._ID + "= ?";
        String evenscselection = EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ?";
        String eventlyselection = EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
        String evusselection = EventUserEntry.COLUMN_EVENT_ID + " = ?";
        String[] selectionArgs = {Long.valueOf((EventEntry.getEventIdFromUri(mUri))).toString()};

        Cursor cScd = getContext().getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, evenscselection, selectionArgs, evenscSortOrder);
        String scorecardid;
        if (cScd.moveToFirst()) {
            do{
                scorecardid = Long.valueOf(cScd.getLong(COL_SC_ID)).toString();
                String scdSelection = ScorecardEntry._ID + " = ?";
                String[] scdSelectionArgs = {scorecardid};
                getContext().getContentResolver().delete(ScorecardEntry.CONTENT_URI, scdSelection, scdSelectionArgs);
                cScd.moveToNext();
            }while(!cScd.isAfterLast());
        }

        Cursor cTly = getContext().getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, eventlyselection, selectionArgs, eventlySortOrder);
        String tallyid;
        if (cTly.moveToFirst()) {
            do{
                tallyid = Long.valueOf(cTly.getLong(COL_TLY_ID)).toString();
                String tlySelection = TallyEntry._ID + " = ?";
                String[] tlySelectionArgs = {tallyid};
                getContext().getContentResolver().delete(TallyEntry.CONTENT_URI, tlySelection, tlySelectionArgs);
                cTly.moveToNext();
            }while(!cTly.isAfterLast());
        }

        getContext().getContentResolver().delete(EventEntrantScorecardEntry.CONTENT_URI, evenscselection, selectionArgs);
        getContext().getContentResolver().delete(EventEntrantTallyEntry.CONTENT_URI, eventlyselection, selectionArgs);
        getContext().getContentResolver().delete(EventUserEntry.CONTENT_URI, evusselection, selectionArgs);
        getContext().getContentResolver().delete(EventEntry.CONTENT_URI, evselection, selectionArgs);
    }

    private void editEvent(Uri itemUri, long l){
        ((Callback) getActivity()).onEventEdit(itemUri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mEventListAdapter = new EventListAdapter(getActivity(), null, 0);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_event, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listview_events);

        registerForContextMenu(mListView);

        mListView.setAdapter(mEventListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);

                if (cursor != null) {
                    ((Callback) getActivity()).onItemSelected(EventEntry.buildEventIdNameUri(mEventListAdapter.getCursor().getLong(COL_EVENT_ID), mEventListAdapter.getCursor().getString(COL_NAME)));
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
        getLoaderManager().initLoader(EVENT_LOADER, null, this);
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

        String sortOrder = EventEntry.COLUMN_DATE + " ASC";
        return new CursorLoader(getActivity(), EventEntry.CONTENT_URI, EVENT_COLUMNS, null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        mEventListAdapter.swapCursor(data);

        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){  // Lesson 5.14
        mEventListAdapter.swapCursor(null);
    }
}