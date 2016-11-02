package com.example.android.scoresheet.app;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.scoresheet.app.data.ScoreSheetContract;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantTallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import android.support.v4.app.Fragment;

/**
 * Created by Kari Stromsland on 10/10/2016.
 */
public class ResultsFragment extends Fragment {
    public static final String LOG_TAG = ResultsFragment.class.getSimpleName();
    static final String RESULTS_URI = "URI";
    private Uri mUri;
    ArrayAdapter<String> mPlacingAdapter;

    private static final String[] ENTRANT_COLUMNS = {
            ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EntrantEntry._ID,
            ScoreSheetContract.EntrantEntry.COLUMN_FIRST_NAME,
            ScoreSheetContract.EntrantEntry.COLUMN_LAST_NAME,
            ScoreSheetContract.EntrantEntry.COLUMN_ID_NUMBER,
            ScoreSheetContract.EntrantEntry.COLUMN_DOG_NAME,
            ScoreSheetContract.EntrantEntry.COLUMN_DOG_ID_NUMBER,
            ScoreSheetContract.EntrantEntry.COLUMN_BREED
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_FIRST_NAME = 1;
    static final int COL_LAST_NAME = 2;
    static final int COL_ID = 3;
    static final int COL_DOG_NAME = 4;
    static final int COL_DOG_ID = 5;
    static final int COL_BREED = 6;

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mIdEditText;
    private EditText mDogNameEditText;
    private EditText mDogIdEditText;
    private EditText mBreedEditText;

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

    private EditText mTotalTimeEditText;
    private EditText mTotalFaultsEditText;
    private EditText mTotalPointsEditText;
    private EditText mTitleEditText;
    private EditText mQualifyingScoreEditText;
    private EditText mQualifyingScoresEditText;

    private static final String[] EVENTENTRANTTALLY_COLUMNS = {
            EventEntrantTallyEntry.TABLE_NAME + "." + EventEntrantTallyEntry._ID,
            EventEntrantTallyEntry.COLUMN_EVENT_ID, EventEntrantTallyEntry.COLUMN_ENTRANT_ID, EventEntrantTallyEntry.COLUMN_TALLY_ID
    };
    public static final int COL_EVENTENTRANTTALLY_ID = 0;
    public static final int COL_EVTLY_ID = 1;
    public static final int COL_ENTLY_ID = 2;
    public static final int COL_TLY_ID = 3;

    private TextView mFirstPlaceText;
    private TextView mFirstPlaceNums;

    public ResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_results, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(ResultsFragment.RESULTS_URI);
        }

        String eventid = Long.valueOf(EventEntry.getEventIdFromUri(mUri)).toString();

        String[] orderedTallies = Utilities.placeOrder(getContext(), eventid);

        String EvEnTlySortOrder = EventEntrantTallyEntry._ID + " ASC";
        String EvEnTlySelection = EventEntrantTallyEntry.COLUMN_EVENT_ID + " = ?";
        String[] EvEnTlySelectionArgs = {eventid};

        Cursor cEvEnTly = getContext().getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, EvEnTlySelection, EvEnTlySelectionArgs, EvEnTlySortOrder);

        String[] names = new String[cEvEnTly.getCount()];

        for (int i=0; i<orderedTallies.length; i++) {
            if (cEvEnTly.moveToFirst()) {
                do {
                    String tallyid = cEvEnTly.getString(COL_TLY_ID);
                    String entrantid = cEvEnTly.getString(COL_ENTLY_ID);
                    if (tallyid.equals(orderedTallies[i])) {
                        String tlySortOrder = TallyEntry._ID + " ASC";
                        String tlySelection = TallyEntry._ID + " = ?";
                        String[] tlySelectionArgs = {tallyid};
                        Cursor cTly = getContext().getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, tlySelection, tlySelectionArgs, tlySortOrder);
                        String enSortOrder = EntrantEntry._ID + " ASC";
                        String enSelection = EntrantEntry._ID + " = ?";
                        String[] enSelectionArgs = {entrantid};
                        Cursor cEn = getContext().getContentResolver().query(EntrantEntry.CONTENT_URI, ENTRANT_COLUMNS, enSelection, enSelectionArgs, enSortOrder);
                        if (cTly.moveToFirst() && cEn.moveToFirst()) {
                            String tempStr = cEn.getString(COL_FIRST_NAME) + " " + cEn.getString(COL_LAST_NAME) + " and " + cEn.getString(COL_DOG_NAME) + "\n" + "Time: " + cTly.getString(COL_TOTAL_TIME) + " " + "Points: " + cTly.getString(COL_TOTAL_POINTS) + " " + "Faults: " + cTly.getString(COL_TOTAL_FAULTS);
                            names[i] = tempStr;
                        }
                        break;
                    }
                    cEvEnTly.moveToNext();
                } while (!cEvEnTly.isAfterLast());
            }
        }

        List<String> placing = new ArrayList<String>(Arrays.asList(names));

        mPlacingAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_results, R.id.list_item_placing, placing);
        ListView listView = (ListView) rootView.findViewById(R.id.placing_list_view);
        listView.setAdapter(mPlacingAdapter);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
