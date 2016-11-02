package com.example.android.scoresheet.app.Scorecards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.Utilities;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;

/**
 * Created by Kari Stromsland on 10/24/2016.
 */
public class StopwatchFragment extends Fragment{

    static final String STOPWATCH_URI = "URI";

    private Button startButton;
    private Button stopButton;
    private Button saveButton;

    private Uri mUri;

    private TextView timerValue;

    private long startTime = 0L;

    private Handler customHandler = new Handler();

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

    public StopwatchFragment() {
        // Required empty public constructor
    }

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    long elapsedTime = 0L;

    public interface Callback {

        public void onTimeSave(Uri timeUri);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(ScorecardEditFragment.SCORECARDEDIT_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        timerValue = (TextView) rootView.findViewById(R.id.timerValue);

        startButton = (Button) rootView.findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                // TODO:  Start only if max time != 0, max hides is not in error, otherwise send message to udpate fields

                String scorecardid = Long.valueOf(ScorecardEntry.getScorecardIdFromUri(mUri)).toString();

                String hideCount = Utilities.getCheckHideCount(getContext(), scorecardid);

                String sortOrder = ScorecardEntry._ID + " ASC";
                String selection = ScorecardEntry._ID + " = ?";
                String[] selectionArgs = {scorecardid};

                String maxTime =  "0";
                int maximumTime;

                Cursor c = getContext().getContentResolver().query(ScorecardEntry.CONTENT_URI, SCORECARD_COLUMNS, selection, selectionArgs, sortOrder);

                if(c.moveToFirst()){
                    maxTime = c.getString(COL_MAXTM) + ":" + c.getString(COL_MAXTS) + ":00";
                }

                maximumTime = Utilities.str_to_time(maxTime);

                if(hideCount.equals("") && maximumTime != 0){
                    startTime = SystemClock.uptimeMillis();
                    customHandler.postDelayed(updateTimerThread, 0);
                }else{
                    Context context = getContext();
                    CharSequence text = "Invalid hide # or max time";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER|Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        stopButton = (Button) rootView.findViewById(R.id.stopButton);

        stopButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                elapsedTime = timeSwapBuff += timeInMilliseconds;

                customHandler.removeCallbacks(updateTimerThread);

            }
        });

        saveButton = (Button) rootView.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String sortOrder = ScorecardEntry._ID;
                String selection = ScorecardEntry._ID + " = ?";
                String[] selectionArgs = {Long.valueOf(ScorecardEntry.getScorecardIdFromUri(mUri)).toString()};
                String elapsedTimeStr;

                ContentValues mScorecardContentValues = new ContentValues();

                int secs = (int) (elapsedTime / 1000);
                int mins = secs / 60;
                secs = secs % 60;
                long hundredths = (long) Math.round((elapsedTime % 1000)/10);

                elapsedTimeStr = "" + mins + ":"
                        + String.format("%02d", secs) + ":"
                        + String.format("%02d", hundredths);


                mScorecardContentValues.put(ScorecardEntry.COLUMN_TOTAL_TIME, elapsedTimeStr);

                getContext().getContentResolver().update(ScorecardEntry.CONTENT_URI, mScorecardContentValues, selection, selectionArgs);

                if (mUri != null) {
                    ((StopwatchFragment.Callback) getActivity()).onTimeSave(mUri);
                }

            }
        });

//        ScoreSheetSyncAdapter.syncImmediately(getActivity());

        return rootView;

    }




    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
//            int milliseconds = (int) (updatedTime % 1000);
            long hundredths = (long) Math.round((updatedTime % 1000)/10);
            timerValue.setText("" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%02d", hundredths));
            customHandler.postDelayed(this, 0);
        }

    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}