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
            TallyEntry.COLUMN_TALLY_DESC, TallyEntry.COLUMN_EVENTID
    };
    public static final int COL_TALLY_ID = 0;
    public static final int COL_TALLY_DESC = 1;
    public static final int COL_TALLY_EVID = 2;

    static final String TALLYEDIT_URI = "URI";

    private Uri mUri;
    private Uri editUri;
    private EditText mDescrEditText;
    private String DescrEditText;

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

        mDescrEditText = (EditText) rootView.findViewById(R.id.tallyEditText);

        String sortOrder = TallyEntry._ID + " ASC";
        String selection = TallyEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(TallyEntry.getTallyIdFromUri(mUri)).toString()};
        String DescrText;

        Cursor c = getContext().getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            DescrText = c.getString(COL_TALLY_DESC);
            mDescrEditText.setText(DescrText);
        }else {
            mDescrEditText.setText("not found");
        }

        Button save_edit_button = (Button) rootView.findViewById(R.id.tallyEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrEditText = mDescrEditText.getText().toString();

                if ( !(DescrEditText.equals("")) ) {
                    editUri = TallyEntry.buildTallyDescUri(DescrEditText);
                    ContentValues mEditContentValues = new ContentValues();
                    mEditContentValues.put(TallyEntry.COLUMN_TALLY_DESC, DescrEditText);
                    String selection = TallyEntry.COLUMN_TALLY_DESC + " = ?";
                    String[] selectionArgs = {TallyEntry.getTallyDescFromUri(mUri)};
                    getContext().getContentResolver().update(TallyEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);
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