package com.example.android.scoresheet.app;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntrantTallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;

/**
 * Created by Kari Stromsland on 10/10/2016.
 */
public class ResultsFragment extends Fragment{
    public static final String LOG_TAG = ResultsFragment.class.getSimpleName();

    private static final String[] EVENTENTRANTTALLY_COLUMNS = {
            EventEntrantTallyEntry.TABLE_NAME + "." + EventEntrantTallyEntry._ID,
            EventEntrantTallyEntry.COLUMN_EVENT_ID, EventEntrantTallyEntry.COLUMN_ENTRANT_ID, EventEntrantTallyEntry.COLUMN_TALLY_ID
    };
    public static final int COL_EVENTENTRANTTALLY_ID = 0;
    public static final int COL_EVENTID = 1;
    public static final int COL_ENTRANTID = 2;
    public static final int COL_TALLYID = 3;


    private static final String[] TALLY_COLUMNS = {
            TallyEntry.TABLE_NAME + "." + TallyEntry._ID,
            TallyEntry.COLUMN_TITLE
    };
    public static final int COL_TALLY_ID = 0;
    public static final int COL_TITLE = 1;

    static final String EVENTENTRANTTALLY_URI = "URI";

    private Uri mUri;
    private TextView mDescrViewText;

    public ResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_results, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(ResultsFragment.EVENTENTRANTTALLY_URI);
        }

        // TODO: Loop through all tallies by rank order

        mDescrViewText = (TextView) rootView.findViewById(R.id.results_textview);

//        String sortOrder = EventEntrantTallyEntry._ID + " ASC";
//        String selection = EventEntrantTallyEntry._ID + " = ?";
//        String[] selectionArgs = {Long.valueOf(EventEntrantTallyEntry.getEventEntrantTallyIdFromUri(mUri)).toString()};
//        String DescrText;
//
//        Cursor c = getContext().getContentResolver().query(EventEntrantTallyEntry.CONTENT_URI, EVENTENTRANTTALLY_COLUMNS, selection, selectionArgs, sortOrder);
//
//        if(c.moveToFirst()) {
//
//            String tlySortOrder = TallyEntry._ID + " ASC";
//            String tlySelection = TallyEntry._ID + " = ?";
//            String[] tlySelectionArgs = {c.getString(COL_TALLYID).toString()};
//
//            Cursor cTly = getContext().getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, selection, selectionArgs, sortOrder);
//
//            DescrText = cTly.getString(COL_TALLY_DESC);
            mDescrViewText.setText("Results");
//        }else {
//            mDescrViewText.setText("not found");
//        }
        // TODO: Loop through all tallies by rank order

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
