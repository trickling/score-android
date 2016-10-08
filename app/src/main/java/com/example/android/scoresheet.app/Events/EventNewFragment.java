package com.example.android.scoresheet.app.Events;

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
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;

/**
 * Created by Kari Stromsland on 9/9/2016.
 */
public class EventNewFragment extends Fragment {

    public static final String LOG_TAG = EventNewFragment.class.getSimpleName();

    private static final String[] EVENT_COLUMNS = {
            EventEntry.TABLE_NAME + "." + EventEntry._ID,
            EventEntry.COLUMN_SHORT_DESC
    };
    static final int COL_EVENT_ID = 0;
    static final int COL_EVENT_DESC = 1;

    private static final String[] TALLY_DETAIL_COLUMNS = {
            TallyEntry.TABLE_NAME + "." + TallyEntry._ID,
            TallyEntry.COLUMN_TALLY_DESC, TallyEntry.COLUMN_EVENTID
    };
    public static final int COL_TALLY_ID = 0;
    public static final int COL_TALLY_DESC = 1;
    public static final int COL_TALLY_EVID = 2;

    static final String EVENTNEW_URI = "URI";

    private Uri mUri;
    private Uri newUri;
    private Uri tlyUri;
    private EditText mDescrNewText;
    private String DescrNewText;
    private Boolean mNewTextFocus;

    public EventNewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_new_event, container, false);

        mDescrNewText = (EditText) rootView.findViewById(R.id.eventNewText);

        Button save_new_button = (Button) rootView.findViewById(R.id.eventNewSave);

        View.OnClickListener new_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrNewText = mDescrNewText.getText().toString();
                if ( !(DescrNewText.equals("")) ) {
                    mUri = EventEntry.buildEventDescUri(DescrNewText);
                    ContentValues mNewContentValues = new ContentValues();
                    mNewContentValues.put(EventEntry.COLUMN_SHORT_DESC,DescrNewText);
                    newUri = getContext().getContentResolver().insert(EventEntry.CONTENT_URI, mNewContentValues);
                    String eventid = Long.valueOf(EventEntry.getEventIdFromUri(newUri)).toString();
                    String tlydescr = DescrNewText + " Tally";
                    ContentValues tlyNewContentValues = new ContentValues();
                    tlyNewContentValues.put(TallyEntry.COLUMN_EVENTID, eventid);
                    tlyNewContentValues.put(TallyEntry.COLUMN_TALLY_DESC, tlydescr);
                    tlyUri = getContext().getContentResolver().insert(TallyEntry.CONTENT_URI, tlyNewContentValues);
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
