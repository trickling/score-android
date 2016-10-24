package com.example.android.scoresheet.app.Tallies;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;

/**
 * Created by Kari Stromsland on 10/7/2016.
 */
public class TallyEditFragment extends Fragment{
    public static final String LOG_TAG = TallyEditFragment.class.getSimpleName();

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

    static final String TALLYEDIT_URI = "URI";

    private Uri mUri;
    private Uri editUri;
    private EditText mTotalTimeEditText;
    private EditText mTotalFaultsEditText;
    private EditText mTotalPointsEditText;
    private EditText mTitleEditText;
    private EditText mQualifyingScoreEditText;
    private EditText mQualifyingScoresEditText;

    public TallyEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(TallyEditFragment.TALLYEDIT_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_edit_tally, container, false);

        mTotalTimeEditText = (EditText) rootView.findViewById(R.id.tally_total_time_text_edit);
        mTotalFaultsEditText = (EditText) rootView.findViewById(R.id.tally_total_faults_text_edit);
        mTotalPointsEditText = (EditText) rootView.findViewById(R.id.tally_total_points_text_edit);
        mTitleEditText = (EditText) rootView.findViewById(R.id.tally_title_text_edit);
        mQualifyingScoreEditText = (EditText) rootView.findViewById(R.id.tally_qualifying_score_text_edit);
        mQualifyingScoresEditText = (EditText) rootView.findViewById(R.id.tally_qualifying_scores_text_edit);

        String sortOrder = TallyEntry._ID + " ASC";
        String selection = TallyEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(TallyEntry.getTallyIdFromUri(mUri)).toString()};

        Cursor c = getContext().getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {

            mTotalTimeEditText.setText(c.getString(COL_TOTAL_TIME));
            mTotalFaultsEditText.setText(c.getString(COL_TOTAL_FAULTS));
            mTotalPointsEditText.setText(c.getString(COL_TOTAL_POINTS));
            mTitleEditText.setText(c.getString(COL_TITLE));
            mQualifyingScoreEditText.setText(c.getString(COL_QSCORE));
            mQualifyingScoresEditText.setText(c.getString(COL_QSCORES));
        }

        Button save_edit_button = (Button) rootView.findViewById(R.id.tallyEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {

            public void onClick(View v) {

                ContentValues mEditContentValues = new ContentValues();

                mEditContentValues.put(TallyEntry.COLUMN_TOTAL_TIME, mTotalTimeEditText.getText().toString());
                mEditContentValues.put(TallyEntry.COLUMN_TOTAL_FAULTS, mTotalFaultsEditText.getText().toString());
                mEditContentValues.put(TallyEntry.COLUMN_TOTAL_POINTS, mTotalPointsEditText.getText().toString());
                mEditContentValues.put(TallyEntry.COLUMN_TITLE, mTitleEditText.getText().toString());
                mEditContentValues.put(TallyEntry.COLUMN_QUALIFYING_SCORE, mQualifyingScoreEditText.getText().toString());
                mEditContentValues.put(TallyEntry.COLUMN_QUALIFYING_SCORES, mQualifyingScoresEditText.getText().toString());

                String selection = TallyEntry._ID + " = ?";
                String[] selectionArgs = {Long.valueOf(TallyEntry.getTallyIdFromUri(mUri)).toString()};
                getContext().getContentResolver().update(TallyEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);
            }
        };

        save_edit_button.setOnClickListener(edit_saveOnClickListener);

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