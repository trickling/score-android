package com.example.android.scoresheet.app.Events;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;

/**
 * Created by Kari Stromsland on 9/9/2016.
 */
public class EventNewFragment extends Fragment{

    public static final String LOG_TAG = EventNewFragment.class.getSimpleName();
    private Uri mUri;
    private Uri newUri;

//    private static final int EVENT_LOADER = 0;

    private static final String[] EVENT_COLUMNS = {
            ScoreSheetContract.EventEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntry._ID,
            EventEntry.COLUMN_NAME,
            EventEntry.COLUMN_LOCATION,
            EventEntry.COLUMN_DATE,
            EventEntry.COLUMN_HOST,
            EventEntry.COLUMN_STATUS,
            EventEntry.COLUMN_DIVISION,
            EventEntry.COLUMN_INT_SEARCH_AREAS,
            EventEntry.COLUMN_EXT_SEARCH_AREAS,
            EventEntry.COLUMN_CONT_SEARCH_AREAS,
            EventEntry.COLUMN_VEH_SEARCH_AREAS,
            EventEntry.COLUMN_ELITE_SEARCH_AREAS,
            EventEntry.COLUMN_INT_HIDES,
            EventEntry.COLUMN_EXT_HIDES,
            EventEntry.COLUMN_CONT_HIDES,
            EventEntry.COLUMN_VEH_HIDES,
            EventEntry.COLUMN_ELITE_HIDES

    };
    public static final int COL_EVENT_ID = 0;
    public static final int COL_NAME = 1;
    public static final int COL_LOC = 2;
    public static final int COL_DATE = 3;
    public static final int COL_HOST = 4;
    public static final int COL_STATUS = 5;
    public static final int COL_DIV = 6;
    public static final int COL_INT_SA = 7;
    public static final int COL_EXT_SA = 8;
    public static final int COL_CONT_SA = 9;
    public static final int COL_VEH_SA = 10;
    public static final int COL_ELITE_SA = 11;
    public static final int COL_INT_HD = 12;
    public static final int COL_EXT_HD = 13;
    public static final int COL_CONT_HD = 14;
    public static final int COL_VEH_HD = 15;
    public static final int COL_ELITE_HD = 16;

    static final String EVENTNEW_URI = "URI";

    private TextView mNameNewText;
    private TextView mLocationNewText;
    private TextView mDateNewText;
    private TextView mHostNewText;
    private TextView mStatusNewText;
    private TextView mDivisionNewText;
    private TextView mIntSearchAreasNewText;
    private TextView mExtSearchAreasNewText;
    private TextView mContSearchAreasNewText;
    private TextView mVehSearchAreasNewText;
    private TextView mEliteSearchAreasNewText;
    private TextView mIntHidesNewText;
    private TextView mExtHidesNewText;
    private TextView mContHidesNewText;
    private TextView mVehHidesNewText;
    private TextView mEliteHidesNewText;

    public EventNewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_new_event, container, false);

        mNameNewText = (TextView) rootView.findViewById(R.id.event_name_text_edit);
        mLocationNewText = (TextView) rootView.findViewById(R.id.event_loc_text_edit);
        mDateNewText = (TextView) rootView.findViewById(R.id.event_date_text_edit);
        mHostNewText = (TextView) rootView.findViewById(R.id.event_host_text_edit);
        mStatusNewText = (TextView) rootView.findViewById(R.id.event_status_text_edit);
        mDivisionNewText = (TextView) rootView.findViewById(R.id.event_division_text_edit);
        mIntSearchAreasNewText = (TextView) rootView.findViewById(R.id.event_int_sa_text_edit);
        mExtSearchAreasNewText = (TextView) rootView.findViewById(R.id.event_ext_sa_text_edit);
        mContSearchAreasNewText = (TextView) rootView.findViewById(R.id.event_cont_sa_text_edit);
        mVehSearchAreasNewText = (TextView) rootView.findViewById(R.id.event_veh_sa_text_edit);
        mEliteSearchAreasNewText = (TextView) rootView.findViewById(R.id.event_elite_sa_text_edit);
        mIntHidesNewText = (TextView) rootView.findViewById(R.id.event_int_hd_text_edit);
        mExtHidesNewText = (TextView) rootView.findViewById(R.id.event_ext_hd_text_edit);
        mContHidesNewText = (TextView) rootView.findViewById(R.id.event_cont_hd_text_edit);
        mVehHidesNewText = (TextView) rootView.findViewById(R.id.event_veh_hd_text_edit);
        mEliteHidesNewText = (TextView) rootView.findViewById(R.id.event_elite_hd_text_edit);

        Button save_new_button = (Button) rootView.findViewById(R.id.eventNewSave);

        View.OnClickListener new_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                ContentValues mNewContentValues = new ContentValues();

                mNewContentValues.put(EventEntry.COLUMN_NAME, mNameNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_LOCATION, mLocationNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_DATE, mDateNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_HOST, mHostNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_STATUS, mStatusNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_DIVISION, mDivisionNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_INT_SEARCH_AREAS, mIntSearchAreasNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_EXT_SEARCH_AREAS, mExtSearchAreasNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_CONT_SEARCH_AREAS, mContSearchAreasNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_VEH_SEARCH_AREAS, mContSearchAreasNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_ELITE_SEARCH_AREAS, mEliteSearchAreasNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_INT_HIDES, mIntHidesNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_EXT_HIDES, mExtHidesNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_CONT_HIDES, mContHidesNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_VEH_HIDES, mVehHidesNewText.getText().toString());
                mNewContentValues.put(EventEntry.COLUMN_ELITE_HIDES, mEliteHidesNewText.getText().toString());
                newUri = getContext().getContentResolver().insert(EventEntry.CONTENT_URI, mNewContentValues);

            }
        };

        save_new_button.setOnClickListener(new_saveOnClickListener);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
//        getLoaderManager().initLoader(EVENT_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }


//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle){
//
//        if (null != mUri) {
//            return new CursorLoader(getActivity(), mUri, EVENT_COLUMNS, null, null, null);
//        }
//        return null;
//    }

//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
//
//        ContentValues mNewContentValues = new ContentValues();
//
//        if (data != null && data.moveToFirst()) {
//            String name = data.getString(COL_NAME);
//            mNewContentValues.put(EventEntry.COLUMN_NAME, name);
//
//            String location = data.getString(COL_LOC);
//            mNewContentValues.put(EventEntry.COLUMN_LOCATION, location);
//
//            String date = data.getString(COL_DATE);
//            mNewContentValues.put(EventEntry.COLUMN_DATE, date);
//
//            String host = data.getString(COL_HOST);
//            mNewContentValues.put(EventEntry.COLUMN_HOST, host);
//
//            String status = data.getString(COL_STATUS);
//            mNewContentValues.put(EventEntry.COLUMN_STATUS, status);
//
//            String division = data.getString(COL_DIV);
//            mNewContentValues.put(EventEntry.COLUMN_DIVISION, division);
//
//            String int_search_areas = data.getString(COL_INT_SA);
//            mNewContentValues.put(EventEntry.COLUMN_INT_SEARCH_AREAS, int_search_areas);
//
//            String ext_search_areas = data.getString(COL_EXT_SA);
//            mNewContentValues.put(EventEntry.COLUMN_EXT_SEARCH_AREAS, ext_search_areas);
//
//            String cont_search_areas = data.getString(COL_CONT_SA);
//            mNewContentValues.put(EventEntry.COLUMN_CONT_SEARCH_AREAS, cont_search_areas);
//
//            String veh_search_areas = data.getString(COL_VEH_SA);
//            mNewContentValues.put(EventEntry.COLUMN_VEH_SEARCH_AREAS, veh_search_areas);
//
//            String elite_search_areas = data.getString(COL_ELITE_SA);
//            mNewContentValues.put(EventEntry.COLUMN_ELITE_SEARCH_AREAS, elite_search_areas);
//
//            String int_hides = data.getString(COL_INT_HD);
//            mNewContentValues.put(EventEntry.COLUMN_INT_HIDES, int_hides);
//
//            String ext_hides = data.getString(COL_EXT_HD);
//            mNewContentValues.put(EventEntry.COLUMN_EXT_HIDES, ext_hides);
//
//            String cont_hides = data.getString(COL_CONT_HD);
//            mNewContentValues.put(EventEntry.COLUMN_CONT_HIDES, cont_hides);
//
//            String veh_hides = data.getString(COL_VEH_HD);
//            mNewContentValues.put(EventEntry.COLUMN_VEH_HIDES, veh_hides);
//
//            String elite_hides = data.getString(COL_ELITE_HD);
//            mNewContentValues.put(EventEntry.COLUMN_ELITE_HIDES, elite_hides);
//
////                    mNewContentValues.put(EventEntry.COLUMN_NAME,NameNewText);
////
//        }
//        newUri = getContext().getContentResolver().insert(EventEntry.CONTENT_URI, mNewContentValues);
//    }

//    @Override
//    public void onLoaderReset(Loader<Cursor> loader){
//
//    }

}
