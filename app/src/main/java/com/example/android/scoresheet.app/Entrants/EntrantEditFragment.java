package com.example.android.scoresheet.app.Entrants;

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
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class EntrantEditFragment extends Fragment {

    public static final String LOG_TAG = EntrantEditFragment.class.getSimpleName();

    private static final String[] ENTRANT_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            EntrantEntry.TABLE_NAME + "." + EntrantEntry._ID,
            EntrantEntry.COLUMN_TEAM_DESC
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_ENTRANT_DESC = 1;

    static final String ENTRANTEDIT_URI = "URI";

    private Uri mUri;
    private Uri editUri;
    private EditText mDescrEditText;
    private String DescrEditText;

    public EntrantEditFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EntrantEditFragment.ENTRANTEDIT_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_edit_entrant, container, false);

        mDescrEditText = (EditText) rootView.findViewById(R.id.entrantEditText);

        String sortOrder = EntrantEntry._ID + " ASC";
        String selection = EntrantEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(EntrantEntry.getEntrantIdFromUri(mUri)).toString()};
        String DescrText;

        Cursor c = getContext().getContentResolver().query(EntrantEntry.CONTENT_URI, ENTRANT_COLUMNS, selection, selectionArgs, sortOrder);
        if(c.moveToFirst()) {
            DescrText = c.getString(COL_ENTRANT_DESC);
        }else{
            DescrText = "not found";
        }

        mDescrEditText.setText(DescrText);

        Button save_edit_button = (Button) rootView.findViewById(R.id.entrantEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrEditText = mDescrEditText.getText().toString();

                if ( !(DescrEditText.equals("not found")) ) {
                    editUri = EntrantEntry.buildEntrantDescUri(DescrEditText);
                    ContentValues mEditContentValues = new ContentValues();
                    mEditContentValues.put(EntrantEntry.COLUMN_TEAM_DESC, DescrEditText);
                    String selection = EntrantEntry.COLUMN_TEAM_DESC + " = ?";
                    String[] selectionArgs = {EntrantEntry.getEntrantDescFromUri(mUri)};
                    getContext().getContentResolver().update(EntrantEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);
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