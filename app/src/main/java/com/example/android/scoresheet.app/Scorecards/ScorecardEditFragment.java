package com.example.android.scoresheet.app.Scorecards;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.Utilities;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;

/**
 * Created by Kari Stromsland on 10/7/2016.
 */
public class ScorecardEditFragment extends Fragment {

    public static final String LOG_TAG = ScorecardEditFragment.class.getSimpleName();
    static final String SCORECARDEDIT_URI = "URI";
    private Uri mUri;
    private Uri editUri;

//    private ScorecardListAdapter mScorecardListAdapter;
//    private ListView mListView;

//    private static final String SELECTED_KEY = "selected_position";
//    private int mPosition = ListView.INVALID_POSITION;


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

    private TextView mElementEditText;
    private TextView mSearchAreaEditText;
    private EditText mMaxTimeMEditText;
    private EditText mMaxTimeSEditText;
    private EditText mFinishCallEditText;
    private EditText mFalseAlertFringeEditText;
    private EditText mTimedOutEditText;
    private EditText mDismissedEditText;
    private EditText mExcusedEditText;
    private EditText mAbsentEditText;
    private EditText mEliminatedDuringSearchEditText;
    private EditText mOtherFaultsDescEditText;
    private EditText mOtherFaultsCountEditText;
    private EditText mCommentsEditText;
    private EditText mTotalTimeEditText;
    private EditText mPronouncedEditText;
    private EditText mJudgeSignatureEditText;
    private EditText mHidesMaxEditText;
    private EditText mHidesFoundEditText;
    private EditText mHidesMissedEditText;
    private EditText mTotalFaultsEditText;
    private EditText mMaxPointsEditText;
    private EditText mTotalPointsEditText;

    public ScorecardEditFragment() {
        // Required empty public constructor
    }

    public interface Callback {

        public void onSave(Uri timeUri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(ScorecardEditFragment.SCORECARDEDIT_URI);
        }

//        mScorecardListAdapter = new ScorecardListAdapter(getActivity(), null, 0);

        View rootView = inflater.inflate(R.layout.fragment_edit_scorecard, container, false);

//        mListView = (ListView) rootView.findViewById(R.id.listview_scorecard);

//        mListView.setAdapter(mScorecardListAdapter);

        mElementEditText = (TextView) rootView.findViewById(R.id.scorecard_element_text_edit);
        mSearchAreaEditText = (TextView) rootView.findViewById(R.id.scorecard_search_area_text_edit);
        mMaxTimeMEditText = (EditText) rootView.findViewById(R.id.scorecard_max_time_m_text_edit);
        mMaxTimeSEditText = (EditText) rootView.findViewById(R.id.scorecard_max_time_s_edit);
        mFinishCallEditText = (EditText) rootView.findViewById(R.id.scorecard_finish_call_text_edit);
        mFalseAlertFringeEditText = (EditText) rootView.findViewById(R.id.scorecard_false_alert_fringe_text_edit);
        mTimedOutEditText = (EditText) rootView.findViewById(R.id.scorecard_timed_out_text_edit);
        mDismissedEditText = (EditText) rootView.findViewById(R.id.scorecard_dismissed_text_edit);
        mExcusedEditText = (EditText) rootView.findViewById(R.id.scorecard_excused_text_edit);
        mAbsentEditText = (EditText) rootView.findViewById(R.id.scorecard_absent_text_edit);
        mEliminatedDuringSearchEditText = (EditText) rootView.findViewById(R.id.scorecard_elim_during_search_text_edit);
        mOtherFaultsDescEditText = (EditText) rootView.findViewById(R.id.scorecard_other_faults_desc_text_edit);
        mOtherFaultsCountEditText = (EditText) rootView.findViewById(R.id.scorecard_other_faults_count_text_edit);
        mCommentsEditText = (EditText) rootView.findViewById(R.id.scorecard_comments_text_edit);
        mTotalTimeEditText = (EditText) rootView.findViewById(R.id.scorecard_total_time_text_edit);
        mPronouncedEditText = (EditText) rootView.findViewById(R.id.scorecard_pronounced_text_edit);
        mJudgeSignatureEditText = (EditText) rootView.findViewById(R.id.scorecard_judge_signature_text_edit);
        mHidesMaxEditText = (EditText) rootView.findViewById(R.id.scorecard_hides_max_text_edit);
        mHidesFoundEditText = (EditText) rootView.findViewById(R.id.scorecard_hides_found_text_edit);
        mHidesMissedEditText = (EditText) rootView.findViewById(R.id.scorecard_hides_missed_text_edit);
        mTotalFaultsEditText = (EditText) rootView.findViewById(R.id.scorecard_total_faults_text_edit);
        mMaxPointsEditText = (EditText) rootView.findViewById(R.id.scorecard_max_points_text_edit);
        mTotalPointsEditText = (EditText) rootView.findViewById(R.id.scorecard_total_points_text_edit);

        String sortOrder = ScorecardEntry._ID + " ASC";
        String selection = ScorecardEntry._ID + " = ?";
        final String scorecardid = Long.valueOf(ScorecardEntry.getScorecardIdFromUri(mUri)).toString();
        String[] selectionArgs = {scorecardid};

        Cursor c = getContext().getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            mElementEditText.setText(c.getString(COL_ELEMENT));
            mMaxTimeMEditText.setText(c.getString(COL_MAXTM));
            mMaxTimeSEditText.setText(c.getString(COL_MAXTS));
            mFinishCallEditText.setText(c.getString(COL_FINCALL));
            mFalseAlertFringeEditText.setText(c.getString(COL_FAF));
            mTimedOutEditText.setText(c.getString(COL_TIMEOUT));
            mDismissedEditText.setText(c.getString(COL_DISMISSED));
            mExcusedEditText.setText(c.getString(COL_EXCUSED));
            mAbsentEditText.setText(c.getString(COL_ABSENT));
            mEliminatedDuringSearchEditText.setText(c.getString(COL_EDS));
            mOtherFaultsDescEditText.setText(c.getString(COL_OFD));
            mOtherFaultsCountEditText.setText(c.getString(COL_OFC));
            mCommentsEditText.setText(c.getString(COL_COMMENTS));
            mTotalTimeEditText.setText(c.getString(COL_TOTALT));
            mPronouncedEditText.setText(c.getString(COL_PRON));
            mJudgeSignatureEditText.setText(c.getString(COL_JS));
            mSearchAreaEditText.setText(c.getString(COL_SA));
            mHidesMaxEditText.setText(c.getString(COL_HDMAX));
            mHidesFoundEditText.setText(c.getString(COL_HDFOUND));
            mHidesMissedEditText.setText(c.getString(COL_HDMISSED));
            mTotalFaultsEditText.setText(c.getString(COL_TOTALFLTS));
            mMaxPointsEditText.setText(c.getString(COL_MAXPT));
            mTotalPointsEditText.setText(c.getString(COL_TOTALPTS));
        }


        Button save_init_data_button = (Button) rootView.findViewById(R.id.scorecardInitSave);

        View.OnClickListener save_init_dataOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                ContentValues mEditContentValues = new ContentValues();

                mEditContentValues.put(ScorecardEntry.COLUMN_ELEMENT, mElementEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_SEARCH_AREA, mSearchAreaEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_MAXTIME_M, mMaxTimeMEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_MAXTIME_S, mMaxTimeSEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_HIDES_MAX, mHidesMaxEditText.getText().toString());

                String selection = ScorecardEntry._ID + " = ?";
                String[] selectionArgs = {scorecardid};
                getContext().getContentResolver().update(ScorecardEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);

                Intent intent = new Intent(getContext(), ScorecardEditActivity.class).setData(mUri);
                startActivity(intent);
            }
        };

        save_init_data_button.setOnClickListener(save_init_dataOnClickListener);


        Button compute_button = (Button) rootView.findViewById(R.id.scorecardComputeSave);

        View.OnClickListener computeOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

            ContentValues mEditContentValues = new ContentValues();

            mEditContentValues.put(ScorecardEntry.COLUMN_ELEMENT, mElementEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_MAXTIME_M, mMaxTimeMEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_MAXTIME_S, mMaxTimeSEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_FINISH_CALL, mFinishCallEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_FALSE_ALERT_FRINGE, mFalseAlertFringeEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_TIMED_OUT, mTimedOutEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_DISMISSED, mDismissedEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_EXCUSED, mExcusedEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_ABSENT, mAbsentEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_ELIMINATED_DURING_SEARCH, mEliminatedDuringSearchEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_OTHER_FAULTS_DESCR, mOtherFaultsDescEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_OTHER_FAULTS_COUNT, mOtherFaultsCountEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_COMMENTS, mCommentsEditText.getText().toString());

            mEditContentValues.put(ScorecardEntry.COLUMN_PRONOUNCED, mPronouncedEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_JUDGE_SIGNATURE, mJudgeSignatureEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_SEARCH_AREA, mSearchAreaEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_HIDES_MAX, mHidesMaxEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_HIDES_FOUND, mHidesFoundEditText.getText().toString());
            mEditContentValues.put(ScorecardEntry.COLUMN_HIDES_MISSED, mHidesMissedEditText.getText().toString());

            String selection = ScorecardEntry._ID + " = ?";
            String[] selectionArgs = {scorecardid};
            getContext().getContentResolver().update(ScorecardEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);

            String time = Utilities.getTime(getContext(), scorecardid);
            mEditContentValues.put(ScorecardEntry.COLUMN_TOTAL_TIME, time);
            String totalFaults = Utilities.getFaultTotal(getContext(), scorecardid);
            mEditContentValues.put(ScorecardEntry.COLUMN_TOTAL_FAULTS, totalFaults);
            mEditContentValues.put(ScorecardEntry.COLUMN_MAXPOINT, mMaxPointsEditText.getText().toString());
            String totalPoints  = Utilities.getPoints(getContext(), scorecardid);
            mEditContentValues.put(ScorecardEntry.COLUMN_TOTAL_POINTS, totalPoints);

            getContext().getContentResolver().update(ScorecardEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);

            Intent intent = new Intent(getContext(), ScorecardEditActivity.class).setData(mUri);
            startActivity(intent);

            }
        };

        compute_button.setOnClickListener(computeOnClickListener);


        Button save_final_button = (Button) rootView.findViewById(R.id.scorecardFinalSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                ContentValues mEditContentValues = new ContentValues();

                mEditContentValues.put(ScorecardEntry.COLUMN_ELEMENT, mElementEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_MAXTIME_M, mMaxTimeMEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_MAXTIME_S, mMaxTimeSEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_FINISH_CALL, mFinishCallEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_FALSE_ALERT_FRINGE, mFalseAlertFringeEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_TIMED_OUT, mTimedOutEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_DISMISSED, mDismissedEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_EXCUSED, mExcusedEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_ABSENT, mAbsentEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_ELIMINATED_DURING_SEARCH, mEliminatedDuringSearchEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_OTHER_FAULTS_DESCR, mOtherFaultsDescEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_OTHER_FAULTS_COUNT, mOtherFaultsCountEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_COMMENTS, mCommentsEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_TOTAL_TIME, mTotalTimeEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_PRONOUNCED, mPronouncedEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_JUDGE_SIGNATURE, mJudgeSignatureEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_SEARCH_AREA, mSearchAreaEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_HIDES_MAX, mHidesMaxEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_HIDES_FOUND, mHidesFoundEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_HIDES_MISSED, mHidesMissedEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_TOTAL_FAULTS, mTotalFaultsEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_MAXPOINT, mMaxPointsEditText.getText().toString());
                mEditContentValues.put(ScorecardEntry.COLUMN_TOTAL_POINTS, mTotalPointsEditText.getText().toString());

                String selection = ScorecardEntry._ID + " = ?";
                String[] selectionArgs = {scorecardid};
                getContext().getContentResolver().update(ScorecardEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);

                Intent intent = new Intent(getContext(), ScorecardViewActivity.class).setData(mUri);
                startActivity(intent);
            }
        };

        save_final_button.setOnClickListener(edit_saveOnClickListener);

//        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
//            mPosition = savedInstanceState.getInt(SELECTED_KEY);
//        }

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