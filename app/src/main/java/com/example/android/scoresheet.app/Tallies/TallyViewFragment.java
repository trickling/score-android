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
import com.example.android.scoresheet.app.data.ScoreSheetContract.TallyEntry;

/**
 * Created by Kari Stromsland on 10/7/2016.
 */
public class TallyViewFragment extends Fragment{
    public static final String LOG_TAG = TallyViewFragment.class.getSimpleName();

    private static final String[] TALLY_COLUMNS = {
            TallyEntry.TABLE_NAME + "." + TallyEntry._ID,
            TallyEntry.COLUMN_TALLY_DESC, TallyEntry.COLUMN_EVENTID
    };
    public static final int COL_TALLY_ID = 0;
    public static final int COL_TALLY_DESC = 1;
    public static final int COL_TALLY_EVID = 2;

    static final String TALLYVIEW_URI = "URI";

    private Uri mUri;
    private TextView mDescrViewText;

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

        mDescrViewText = (TextView) rootView.findViewById(R.id.tally_text_view);

        String sortOrder = TallyEntry._ID + " ASC";
        String selection = TallyEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(TallyEntry.getTallyIdFromUri(mUri)).toString()};
        String DescrText;

        Cursor c = getContext().getContentResolver().query(TallyEntry.CONTENT_URI, TALLY_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            DescrText = c.getString(COL_TALLY_DESC);
            mDescrViewText.setText(DescrText);
        }else {
            mDescrViewText.setText("not found");
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