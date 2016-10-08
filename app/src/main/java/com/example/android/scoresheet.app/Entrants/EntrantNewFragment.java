package com.example.android.scoresheet.app.Entrants;

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
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class EntrantNewFragment extends Fragment {

    public static final String LOG_TAG = EntrantNewFragment.class.getSimpleName();

    private static final String[] ENTRANT_COLUMNS = {
            EntrantEntry.TABLE_NAME + "." + EntrantEntry._ID,
            EntrantEntry.COLUMN_TEAM_DESC
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_ENTRANT_DESC = 1;

    static final String ENTRANTNEW_URI = "URI";

    private Uri mUri;
    private Uri newUri;
    private EditText mDescrNewText;
    private String DescrNewText;
    private Boolean mNewTextFocus;

    public EntrantNewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_new_entrant, container, false);

        mDescrNewText = (EditText) rootView.findViewById(R.id.entrantNewText);

        Button save_new_button = (Button) rootView.findViewById(R.id.entrantNewSave);

        View.OnClickListener new_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrNewText = mDescrNewText.getText().toString();
                if ( !(DescrNewText.equals("")) ) {
                    mUri = EntrantEntry.buildEntrantDescUri(DescrNewText);
                    ContentValues mNewContentValues = new ContentValues();
                    mNewContentValues.put(EntrantEntry.COLUMN_TEAM_DESC,DescrNewText);
                    newUri = getContext().getContentResolver().insert(EntrantEntry.CONTENT_URI, mNewContentValues);
                }
            }
        };

        save_new_button.setOnClickListener(new_saveOnClickListener);

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