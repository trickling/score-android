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

    static final String SCORECARDVIEW_URI = "URI";

    private Uri mUri;

    private TextView mElementViewText;
    private TextView mMaxTimeMViewText;
    private TextView mMaxTimeSViewText;
    private TextView mFinishCallViewText;
    private TextView mFalseAlertFringeViewText;
    private TextView mTimedOutViewText;
    private TextView mDismissedViewText;
    private TextView mExcusedViewText;
    private TextView mAbsentViewText;
    private TextView mEliminatedDuringSearchViewText;
    private TextView mOtherFaultsDescViewText;
    private TextView mOtherFaultsCountViewText;
    private TextView mCommentsViewText;
    private TextView mTotalTimeViewText;
    private TextView mPronouncedViewText;
    private TextView mJudgeSignatureViewText;
    private TextView mSearchAreaViewText;
    private TextView mHidesMaxViewText;
    private TextView mHidesFoundViewText;
    private TextView mHidesMissedViewText;
    private TextView mTotalFaultsViewText;
    private TextView mMaxPointsViewText;
    private TextView mTotalPointsViewText;

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

        mElementViewText = (TextView) rootView.findViewById(R.id.scorecard_element_text_view);
        mMaxTimeMViewText = (TextView) rootView.findViewById(R.id.scorecard_max_time_m_text_view);
        mMaxTimeSViewText = (TextView) rootView.findViewById(R.id.scorecard_max_time_s_view);
        mFinishCallViewText = (TextView) rootView.findViewById(R.id.scorecard_finish_call_text_view);
        mFalseAlertFringeViewText = (TextView) rootView.findViewById(R.id.scorecard_false_alert_fringe_text_view);
        mTimedOutViewText = (TextView) rootView.findViewById(R.id.scorecard_timed_out_text_view);
        mDismissedViewText = (TextView) rootView.findViewById(R.id.scorecard_dismissed_text_view);
        mExcusedViewText = (TextView) rootView.findViewById(R.id.scorecard_excused_text_view);
        mAbsentViewText = (TextView) rootView.findViewById(R.id.scorecard_absent_text_view);
        mEliminatedDuringSearchViewText = (TextView) rootView.findViewById(R.id.scorecard_elim_during_search_text_view);
        mOtherFaultsDescViewText = (TextView) rootView.findViewById(R.id.scorecard_other_faults_desc_text_view);
        mOtherFaultsCountViewText = (TextView) rootView.findViewById(R.id.scorecard_other_faults_count_text_view);
        mCommentsViewText = (TextView) rootView.findViewById(R.id.scorecard_comments_text_view);
        mTotalTimeViewText = (TextView) rootView.findViewById(R.id.scorecard_total_time_text_view);
        mPronouncedViewText = (TextView) rootView.findViewById(R.id.scorecard_pronounced_text_view);
        mJudgeSignatureViewText = (TextView) rootView.findViewById(R.id.scorecard_judge_signature_text_view);
        mSearchAreaViewText = (TextView) rootView.findViewById(R.id.scorecard_search_area_text_view);
        mHidesMaxViewText = (TextView) rootView.findViewById(R.id.scorecard_hides_max_text_view);
        mHidesFoundViewText = (TextView) rootView.findViewById(R.id.scorecard_hides_found_text_view);
        mHidesMissedViewText = (TextView) rootView.findViewById(R.id.scorecard_hides_missed_text_view);
        mTotalFaultsViewText = (TextView) rootView.findViewById(R.id.scorecard_total_faults_text_view);
        mMaxPointsViewText = (TextView) rootView.findViewById(R.id.scorecard_max_points_text_view);
        mTotalPointsViewText = (TextView) rootView.findViewById(R.id.scorecard_total_points_text_view);

        String sortOrder = ScorecardEntry._ID + " ASC";
        String selection = ScorecardEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(ScorecardEntry.getScorecardIdFromUri(mUri)).toString()};

        Cursor c = getContext().getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {

            mElementViewText.setText(c.getString(COL_ELEMENT));
            mMaxTimeMViewText.setText(c.getString(COL_MAXTM));
            mMaxTimeSViewText.setText(c.getString(COL_MAXTS));
            mFinishCallViewText.setText(c.getString(COL_FINCALL));
            mFalseAlertFringeViewText.setText(c.getString(COL_FAF));
            mTimedOutViewText.setText(c.getString(COL_TIMEOUT));
            mDismissedViewText.setText(c.getString(COL_DISMISSED));
            mExcusedViewText.setText(c.getString(COL_EXCUSED));
            mAbsentViewText.setText(c.getString(COL_ABSENT));
            mEliminatedDuringSearchViewText.setText(c.getString(COL_EDS));
            mOtherFaultsDescViewText.setText(c.getString(COL_OFD));
            mOtherFaultsCountViewText.setText(c.getString(COL_OFC));
            mCommentsViewText.setText(c.getString(COL_COMMENTS));
            mTotalTimeViewText.setText(c.getString(COL_TOTALT));
            mPronouncedViewText.setText(c.getString(COL_PRON));
            mJudgeSignatureViewText.setText(c.getString(COL_JS));
            mSearchAreaViewText.setText(c.getString(COL_SA));
            mHidesMaxViewText.setText(c.getString(COL_HDMAX));
            mHidesFoundViewText.setText(c.getString(COL_HDFOUND));
            mHidesMissedViewText.setText(c.getString(COL_HDMISSED));
            mTotalFaultsViewText.setText(c.getString(COL_TOTALFLTS));
            mMaxPointsViewText.setText(c.getString(COL_MAXPT));
            mTotalPointsViewText.setText(c.getString(COL_TOTALPTS));
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