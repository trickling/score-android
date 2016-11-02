package com.example.android.scoresheet.app.Tallies;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.Utilities;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;

/**
 * Created by Kari Stromsland on 10/7/2016.
 */
public class TallyViewFragment extends Fragment{
    public static final String LOG_TAG = TallyViewFragment.class.getSimpleName();
    static final String TALLYVIEW_URI = "URI";
    private Uri mUri;

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

    private TextView mTotalTimeViewText;
    private TextView mTotalFaultsViewText;
    private TextView mTotalPointsViewText;
    private TextView mTitleViewText;
    private TextView mQualifyingScoreViewText;
    private TextView mQualifyingScoresViewText;

    public TallyViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_view_tally, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(TallyViewFragment.TALLYVIEW_URI);
        }

        mTotalTimeViewText = (TextView) rootView.findViewById(R.id.tally_total_time_text_view);
        mTotalFaultsViewText = (TextView) rootView.findViewById(R.id.tally_total_faults_text_view);
        mTotalPointsViewText = (TextView) rootView.findViewById(R.id.tally_total_points_text_view);
        mTitleViewText = (TextView) rootView.findViewById(R.id.tally_title_text_view);
        mQualifyingScoreViewText = (TextView) rootView.findViewById(R.id.tally_qualifying_score_text_view);
        mQualifyingScoresViewText = (TextView) rootView.findViewById(R.id.tally_qualifying_scores_text_view);

        Utilities.getTally(getContext(), Long.valueOf(TallyEntry.getTallyIdFromUri(mUri)).toString());

        String sortOrder = TallyEntry._ID + " ASC";
        String selection = TallyEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(TallyEntry.getTallyIdFromUri(mUri)).toString()};

        Cursor c = getContext().getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            mTotalTimeViewText.setText(c.getString(COL_TOTAL_TIME));
            mTotalFaultsViewText.setText(c.getString(COL_TOTAL_FAULTS));
            mTotalPointsViewText.setText(c.getString(COL_TOTAL_POINTS));
            mTitleViewText.setText(c.getString(COL_TITLE));
            mQualifyingScoreViewText.setText(c.getString(COL_QSCORE));
            mQualifyingScoresViewText.setText(c.getString(COL_QSCORES));
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