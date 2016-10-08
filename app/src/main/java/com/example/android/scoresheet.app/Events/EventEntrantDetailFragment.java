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

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantScorecardEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;
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
    private Uri tlyUri;

    private TextView mEventDescriptionView;
    private TextView mTallyDescriptionView;

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

    private static final String[] TALLY_DETAIL_COLUMNS = {
            TallyEntry.TABLE_NAME + "." + TallyEntry._ID,
            TallyEntry.COLUMN_TALLY_DESC, TallyEntry.COLUMN_EVENTID
    };
    public static final int COL_TALLY_ID = 0;
    public static final int COL_TALLY_DESC = 1;
    public static final int COL_TALLY_EVID = 2;

    public interface Callback {

        public void onEventItemSelected(Uri evDescUri);
        public void onTallyItemSelected(Uri tlyDescUri);
        public void onEntrantSelected(Uri enDescUri);
        public void onEntrantScorecardSelected(Uri scDescUri);
        public void onEntrantScorecardSelectedtoRun(Uri scDescUri);
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
        Cursor c = getContext().getContentResolver().query(EventEntrantScorecardEntry.CONTENT_URI, EVENTENTRANT_COLUMNS, selection, selectionArgs, sortOrder);
        if(c.moveToFirst()){
            scorecardid = Long.valueOf(c.getLong(COL_SC_ID)).toString();
        }else{
            scorecardid = "not found";
        }

        Uri ScUri = EventEntrantScorecardEntry.buildEventEntrantScorecardIdUri(scorecardid);
        Uri eNUri = EventEntrantScorecardEntry.buildEventEntrantIdScorecardUri(entrantid);

        switch (item.getItemId()) {
            case R.id.team:
                viewTeamDetail(eNUri, info.id);
                return true;
            case R.id.scorecard:
                viewScorecard(ScUri, info.id);
                return true;
            case R.id.run_scorecard:
                runScorecard(ScUri, info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

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

        registerForContextMenu(mListView);

        mEventDescriptionView = (TextView) rootView.findViewById(R.id.event_summary_textview);

        mEventDescriptionView.setText(EventEntry.getEventIdDescriptionFromUri(mUri));

        String eventid = Long.valueOf(EventEntry.getEventIdFromUri(mUri)).toString();

        mTallyDescriptionView = (TextView) rootView.findViewById(R.id.event_tally_textview);

        String sortOrder = TallyEntry._ID + " ASC";
        String selection = TallyEntry.COLUMN_EVENTID + " = ?";
        String[] selectionArgs = {eventid};
        Cursor tc = getContext().getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_DETAIL_COLUMNS, selection, selectionArgs, sortOrder);
        String tallyDescr;
        long tallyid;
        if (tc.moveToFirst()) {
            tallyDescr = tc.getString(COL_TALLY_DESC);
            tallyid = tc.getLong(COL_TALLY_ID);
        } else {
            tallyDescr = "not found";
            tallyid = 0;
        }

        tlyUri = TallyEntry.buildTallyIdUri(tallyid);

        mTallyDescriptionView.setText(tallyDescr);


        View.OnClickListener EventDescOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUri != null) {
                    ((Callback) getActivity()).onEventItemSelected(mUri);
                }
            }
        };
        mEventDescriptionView.setOnClickListener(EventDescOnClickListener);


        View.OnClickListener TallyDescOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUri != null) {
                    ((Callback) getActivity()).onTallyItemSelected(tlyUri);
                }
            }
        };
        mTallyDescriptionView.setOnClickListener(TallyDescOnClickListener);



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

    private void runScorecard(Uri itemUri, long l){
        ((Callback) getActivity()).onEntrantScorecardSelectedtoRun(itemUri);
    }
}