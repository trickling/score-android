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
            EntrantEntry.COLUMN_FIRST_NAME,
            EntrantEntry.COLUMN_LAST_NAME,
            EntrantEntry.COLUMN_ID_NUMBER,
            EntrantEntry.COLUMN_DOG_NAME,
            EntrantEntry.COLUMN_DOG_ID_NUMBER,
            EntrantEntry.COLUMN_BREED
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_FIRST_NAME = 1;
    static final int COL_LAST_NAME = 2;
    static final int COL_ID = 3;
    static final int COL_DOG_NAME = 4;
    static final int COL_DOG_ID = 5;
    static final int COL_BREED = 6;

    static final String ENTRANTVIEW_URI = "URI";

    private Uri mUri;
    private TextView mFirstNameViewText;
    private TextView mLastNameViewText;
    private TextView mIdViewText;
    private TextView mDogNameViewText;
    private TextView mDogIdViewText;
    private TextView mBreedViewText;


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

        mFirstNameViewText = (TextView) rootView.findViewById(R.id.entrant_first_name_text_view);
        mLastNameViewText = (TextView) rootView.findViewById(R.id.entrant_last_name_text_view);
        mIdViewText = (TextView) rootView.findViewById(R.id.entrant_id_text_view);
        mDogNameViewText = (TextView) rootView.findViewById(R.id.entrant_dog_name_text_view);
        mDogIdViewText = (TextView) rootView.findViewById(R.id.entrant_dog_id_text_view);
        mBreedViewText = (TextView) rootView.findViewById(R.id.entrant_breed_text_view);

        String sortOrder = EntrantEntry._ID + " ASC";
        String selection = EntrantEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(EntrantEntry.getEntrantIdFromUri(mUri)).toString()};

        Cursor c = getContext().getContentResolver().query(EntrantEntry.CONTENT_URI, ENTRANT_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            mFirstNameViewText.setText(c.getString(COL_FIRST_NAME));
            mLastNameViewText.setText(c.getString(COL_LAST_NAME));
            mIdViewText.setText(c.getString(COL_ID));
            mDogNameViewText.setText(c.getString(COL_DOG_NAME));
            mDogIdViewText.setText(c.getString(COL_DOG_ID));
            mBreedViewText.setText(c.getString(COL_BREED));
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