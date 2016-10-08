package com.example.android.scoresheet.app.Scorecards;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;

/**
 * Created by Kari Stromsland on 10/6/2016.
 */
public class ScorecardViewFragment extends Fragment {
    public static final String LOG_TAG = ScorecardViewFragment.class.getSimpleName();

    private static final String[] SCORECARD_COLUMNS = {
            ScorecardEntry.TABLE_NAME + "." + ScorecardEntry._ID,
            ScorecardEntry.COLUMN_SCORECARD_DESC
    };
    static final int COL_SCORECARD_ID = 0;
    static final int COL_SCORECARD_DESC = 1;

    static final String SCORECARDVIEW_URI = "URI";

    private Uri mUri;
    private TextView mDescrViewText;

    public ScorecardViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_view_scorecard, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(ScorecardViewFragment.SCORECARDVIEW_URI);
        }

        mDescrViewText = (TextView) rootView.findViewById(R.id.scorecard_text_view);

        String sortOrder = ScorecardEntry._ID + " ASC";
        String selection = ScorecardEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(ScorecardEntry.getScorecardIdFromUri(mUri)).toString()};
        String DescrText;

        Cursor c = getContext().getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            DescrText = c.getString(COL_SCORECARD_DESC);
            mDescrViewText.setText(DescrText);
        }else {
            mDescrViewText.setText("not found");
        }

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