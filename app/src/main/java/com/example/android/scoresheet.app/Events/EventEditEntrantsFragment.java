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
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantTallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

//import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantEntry;

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
    private long tallyid;
    private Boolean event_edit_checked;
    static final String EVENTEDITENTRANTS_URI = "URI";
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private static final String intSAelmt = "Interior";
    private static final String extSAelmt = "Exterior";
    private static final String contSAelmt = "Containers";
    private static final String vehSAelmt = "Vehicles";
    private static final String eliteSAelmt = "Elite";

    private static final int EVENTENTRANTS_LOADER = 0;

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

    private static final String[] ENTRANT_COLUMNS = {
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
                        // For each type and number of search areas, create a scorecard
                        // Get search area count and hide info from Event
                        String sortOrder = EventEntry._ID + " ASC";
                        String selection = EventEntry._ID + " = ?";
                        String[] selectionArgs = {Long.valueOf(eventid).toString()};
                        Cursor c = getContext().getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, selection, selectionArgs, sortOrder);

                        Hashtable<String, Integer> searchAreas = new Hashtable<String, Integer>();

                        if (c.moveToFirst()) {
                            searchAreas.put(intSAelmt, Integer.decode(c.getString(COL_INT_SA)));
                            searchAreas.put(extSAelmt, Integer.decode(c.getString(COL_EXT_SA)));
                            searchAreas.put(contSAelmt, Integer.decode(c.getString(COL_CONT_SA)));
                            searchAreas.put(vehSAelmt, Integer.decode(c.getString(COL_VEH_SA)));
                            searchAreas.put(eliteSAelmt, Integer.decode(c.getString(COL_ELITE_SA)));

                        }
                        Set<String> keys = searchAreas.keySet();

                        Iterator<String> itr = keys.iterator();

                        while (itr.hasNext()) {
                            String keySA = itr.next();
                            ContentValues mSCContentValues = new ContentValues();
                            if (searchAreas.get(keySA) > 0) {
                                for (int i = 0; i < searchAreas.get(keySA); i++) {
                                    switch (keySA) {
                                        case intSAelmt:
                                            mSCContentValues.put(ScorecardEntry.COLUMN_SEARCH_AREA, i+1);
                                            mSCContentValues.put(ScorecardEntry.COLUMN_ELEMENT, keySA);
                                            break;
                                        case extSAelmt:
                                            mSCContentValues.put(ScorecardEntry.COLUMN_SEARCH_AREA, i+1);
                                            mSCContentValues.put(ScorecardEntry.COLUMN_ELEMENT, keySA);
                                            break;
                                        case contSAelmt:
                                            mSCContentValues.put(ScorecardEntry.COLUMN_SEARCH_AREA, i+1);
                                            mSCContentValues.put(ScorecardEntry.COLUMN_ELEMENT, keySA);
                                            break;
                                        case vehSAelmt:
                                            mSCContentValues.put(ScorecardEntry.COLUMN_SEARCH_AREA, i+1);
                                            mSCContentValues.put(ScorecardEntry.COLUMN_ELEMENT, keySA);
                                            break;
                                        case eliteSAelmt:
                                            mSCContentValues.put(ScorecardEntry.COLUMN_SEARCH_AREA, i+1);
                                            mSCContentValues.put(ScorecardEntry.COLUMN_ELEMENT, keySA);
                                            break;
                                        default:
                                            break;
                                    }
                                    Uri scUri = getContext().getContentResolver().insert(ScorecardEntry.buildScorecardUri(), mSCContentValues);
                                    scorecardid = ScorecardEntry.getScorecardIdFromUri(scUri);

                                    // TODO get event and entrant info for scorecards if we want it listed on scorecards

                                    ContentValues mscEditContentValues = new ContentValues();
                                    mscEditContentValues.put(EventEntrantScorecardEntry.COLUMN_ENTRANT_ID, entrantid);
                                    mscEditContentValues.put(EventEntrantScorecardEntry.COLUMN_EVENT_ID, eventid);
                                    mscEditContentValues.put(EventEntrantScorecardEntry.COLUMN_SCORECARD_ID, scorecardid);

                                    getContext().getContentResolver().insert(EventEntrantScorecardEntry.buildEventEntrantScorecardUri(), mscEditContentValues);
                                }
                            }
                        }

                        ContentValues mTLYContentValues = new ContentValues();
                        String tallyTitle = "no";
                        mTLYContentValues.put(TallyEntry.COLUMN_TITLE, tallyTitle);
                        Uri tlyUri = getContext().getContentResolver().insert(TallyEntry.buildTallyUri(), mTLYContentValues);
                        tallyid = TallyEntry.getTallyIdFromUri(tlyUri);
                        ContentValues mtlyEditContentValues = new ContentValues();
                        mtlyEditContentValues.put(EventEntrantTallyEntry.COLUMN_ENTRANT_ID, entrantid);
                        mtlyEditContentValues.put(EventEntrantTallyEntry.COLUMN_EVENT_ID, eventid);
                        mtlyEditContentValues.put(EventEntrantTallyEntry.COLUMN_TALLY_ID, tallyid);
                        Uri uri_TLY = EventEntrantTallyEntry.buildEventEntrantTallyUri();
                        getContext().getContentResolver().insert(EventEntrantTallyEntry.buildEventEntrantTallyUri(), mtlyEditContentValues);

                    }else{

                        String sortOrder = EventEntrantScorecardEntry._ID + " ASC";
                        String selection = EventEntrantScorecardEntry.COLUMN_ENTRANT_ID + " = ?" + " AND " + EventEntrantScorecardEntry.COLUMN_EVENT_ID + " = ?";
                        Uri evenScUri = EventEntrantScorecardEntry.buildIdEventEntrantScorecard(Long.valueOf(eventid).toString(), Long.valueOf(entrantid).toString());
                        String[] selectionArgs = {Long.valueOf(entrantid).toString(), Long.valueOf(eventid).toString()};
                        Cursor cScd = getContext().getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANTSCORECARD_COLUMNS, selection, selectionArgs, sortOrder);

                        if(cScd.moveToFirst()) {
                            do {
                                long scorecard_id = cScd.getLong(COL_SC_ID);
                                long evententrant_id = cScd.getLong(COL_EVENTENTRANTSCORECARD_ID);
                                String scSelection = ScorecardEntry._ID + " = ?";
                                String[] scSelectionArgs = {Long.valueOf(scorecard_id).toString()};
                                String dSelection = EventEntrantScorecardEntry._ID + " = ?";
                                String[] dSelectionArgs = {Long.valueOf(evententrant_id).toString()};
                                getContext().getContentResolver().delete(ScorecardEntry.CONTENT_URI, scSelection, scSelectionArgs);
                                getContext().getContentResolver().delete(EventEntrantScorecardEntry.CONTENT_URI, dSelection, dSelectionArgs);
                                cScd.moveToNext();
                            }while(!cScd.isAfterLast());
                        }

                        String tlySortOrder = EventEntrantTallyEntry._ID + " ASC";
                        String evenTlySelection = EventEntrantTallyEntry.COLUMN_ENTRANT_ID + " = ?" + " AND " + EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
                        Uri evenTlyUri = EventEntrantTallyEntry.buildIdEventEntrantTally(Long.valueOf(eventid).toString(), Long.valueOf(entrantid).toString());
                        String[] evenTlySelectionArgs = {Long.valueOf(entrantid).toString(), Long.valueOf(eventid).toString()};
                        Cursor cTly = getContext().getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, evenTlySelection, evenTlySelectionArgs, sortOrder);
                        if(cTly.moveToFirst()) {
                            do {
                                long tally_id = cTly.getLong(COL_TLY_ID);
                                long evententrant_id = cTly.getLong(COL_EVENTENTRANTTALLY_ID);
                                String tlySelection = TallyEntry._ID + " = ?";
                                String[] tlySelectionArgs = {Long.valueOf(tally_id).toString()};
                                String dtlySelection = EventEntrantTallyEntry._ID + " = ?";
                                String[] dtlySelectionArgs = {Long.valueOf(evententrant_id).toString()};
                                getContext().getContentResolver().delete(TallyEntry.CONTENT_URI, tlySelection, tlySelectionArgs);
                                getContext().getContentResolver().delete(EventEntrantTallyEntry.CONTENT_URI, dtlySelection, dtlySelectionArgs);
                                cTly.moveToNext();
                            } while (!cTly.isAfterLast());
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

        String sortOrder = EntrantEntry.COLUMN_LAST_NAME + " ASC";

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