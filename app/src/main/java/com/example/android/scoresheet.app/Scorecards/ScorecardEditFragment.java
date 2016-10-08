package com.example.android.scoresheet.app.Scorecards;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.ScorecardEntry;

/**
 * Created by Kari Stromsland on 10/7/2016.
 */
public class ScorecardEditFragment extends Fragment {
    public static final String LOG_TAG = ScorecardEditFragment.class.getSimpleName();

    private static final String[] SCORECARD_COLUMNS = {
            ScorecardEntry.TABLE_NAME + "." + ScorecardEntry._ID,
            ScorecardEntry.COLUMN_SCORECARD_DESC
    };
    static final int COL_SCORECARD_ID = 0;
    static final int COL_SCORECARD_DESC = 1;

    static final String SCORECARDEDIT_URI = "URI";

    private Uri mUri;
    private Uri editUri;
    private EditText mDescrEditText;
    private String DescrEditText;

    public ScorecardEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(ScorecardEditFragment.SCORECARDEDIT_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_edit_scorecard, container, false);

        mDescrEditText = (EditText) rootView.findViewById(R.id.scorecardEditText);

        mDescrEditText.setText(ScorecardEntry.getScorecardDescFromUri(mUri));

        Button save_edit_button = (Button) rootView.findViewById(R.id.scorecardEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrEditText = mDescrEditText.getText().toString();

                if ( !(DescrEditText.equals("")) ) {
                    editUri = ScorecardEntry.buildScorecardDescUri(DescrEditText);
                    ContentValues mEditContentValues = new ContentValues();
                    mEditContentValues.put(ScorecardEntry.COLUMN_SCORECARD_DESC, DescrEditText);
                    String selection = ScorecardEntry.COLUMN_SCORECARD_DESC + " = ?";
                    String[] selectionArgs = {ScorecardEntry.getScorecardDescFromUri(mUri)};
                    getContext().getContentResolver().update(ScorecardEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);
                }
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