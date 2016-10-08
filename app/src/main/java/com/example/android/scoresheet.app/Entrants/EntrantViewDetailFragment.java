package com.example.android.scoresheet.app.Entrants;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class EntrantViewDetailFragment extends Fragment {

    public static final String LOG_TAG = EntrantViewDetailFragment.class.getSimpleName();

    private static final String[] ENTRANT_COLUMNS = {
            EntrantEntry.TABLE_NAME + "." + EntrantEntry._ID,
            EntrantEntry.COLUMN_TEAM_DESC
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_ENTRANT_DESC = 1;

    static final String ENTRANTVIEW_URI = "URI";

    private Uri mUri;
    private TextView mDescrViewText;

    public EntrantViewDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_detail_view_entrant, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EntrantViewDetailFragment.ENTRANTVIEW_URI);
        }

        mDescrViewText = (TextView) rootView.findViewById(R.id.entrant_text_view);

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


        mDescrViewText.setText(DescrText);

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