package com.example.android.scoresheet.app.Events;

import android.support.v4.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.Utilities;
import com.example.android.scoresheet.app.data.ScoreSheetContract;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;

//import android.support.v4.app.Fragment;

/**
 * Created by Kari Stromsland on 9/15/2016.
 */
public class EventEditFragment extends Fragment {

    public static final String LOG_TAG = EventEditFragment.class.getSimpleName();
    private Uri mUri;
    private Uri eventUri;
    private Uri editUri;
    static final String EVENTEDIT_URI = "URI";

    private static final int EVENT_LOADER = 0;
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

    private String eventid;
    private EditText mNameEditText;
    private EditText mLocationEditText;
    private EditText mDateEditText;
    private EditText mHostEditText;
    private EditText mStatusEditText;
    private EditText mDivisionEditText;
    private EditText mIntSearchAreasEditText;
    private EditText mExtSearchAreasEditText;
    private EditText mContSearchAreasEditText;
    private EditText mVehSearchAreasEditText;
    private EditText mEliteSearchAreasEditText;
    private EditText mIntHidesEditText;
    private EditText mExtHidesEditText;
    private EditText mContHidesEditText;
    private EditText mVehHidesEditText;
    private EditText mEliteHidesEditText;

    public EventEditFragment() {
        // Required empty public constructor
    }

    public interface Callback {

        public void onEventEditEntrants(Uri editUri);
        public void onEventEditUsers(Uri editUri);
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EventEditFragment.EVENTEDIT_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_edit_event, container, false);

        mNameEditText = (EditText) rootView.findViewById(R.id.event_name_text_edit);
        mLocationEditText = (EditText) rootView.findViewById(R.id.event_loc_text_edit);
        mDateEditText = (EditText) rootView.findViewById(R.id.event_date_text_edit);
        mHostEditText = (EditText) rootView.findViewById(R.id.event_host_text_edit);
        mStatusEditText = (EditText) rootView.findViewById(R.id.event_status_text_edit);
        mDivisionEditText = (EditText) rootView.findViewById(R.id.event_division_text_edit);
        mIntSearchAreasEditText = (EditText) rootView.findViewById(R.id.event_int_sa_text_edit);
        mExtSearchAreasEditText = (EditText) rootView.findViewById(R.id.event_ext_sa_text_edit);
        mContSearchAreasEditText = (EditText) rootView.findViewById(R.id.event_cont_sa_text_edit);
        mVehSearchAreasEditText = (EditText) rootView.findViewById(R.id.event_veh_sa_text_edit);
        mEliteSearchAreasEditText = (EditText) rootView.findViewById(R.id.event_elite_sa_text_edit);
        mIntHidesEditText = (EditText) rootView.findViewById(R.id.event_int_hd_text_edit);
        mExtHidesEditText = (EditText) rootView.findViewById(R.id.event_ext_hd_text_edit);
        mContHidesEditText = (EditText) rootView.findViewById(R.id.event_cont_hd_text_edit);
        mVehHidesEditText = (EditText) rootView.findViewById(R.id.event_veh_hd_text_edit);
        mEliteHidesEditText = (EditText) rootView.findViewById(R.id.event_elite_hd_text_edit);

        String sortOrder = EventEntry._ID + " ASC";
        String selection = EventEntry._ID + " = ?";
        eventid = Long.valueOf(EventEntry.getEventIdFromUri(mUri)).toString();
        String[] selectionArgs = {eventid};


        Cursor c = getContext().getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, selection, selectionArgs, sortOrder);
        if(c.moveToFirst()) {
            mNameEditText.setText(c.getString(COL_NAME));
            mLocationEditText.setText(c.getString(COL_LOC));
            mDateEditText.setText(c.getString(COL_DATE));
            mHostEditText.setText(c.getString(COL_HOST));
            mStatusEditText.setText(c.getString(COL_STATUS));
            mDivisionEditText.setText(c.getString(COL_DIV));
            mIntSearchAreasEditText.setText(c.getString(COL_INT_SA));
            mExtSearchAreasEditText.setText(c.getString(COL_EXT_SA));
            mContSearchAreasEditText.setText(c.getString(COL_CONT_SA));
            mVehSearchAreasEditText.setText(c.getString(COL_VEH_SA));
            mEliteSearchAreasEditText.setText(c.getString(COL_ELITE_SA));
            mIntHidesEditText.setText(c.getString(COL_INT_HD));
            mExtHidesEditText.setText(c.getString(COL_EXT_HD));
            mContHidesEditText.setText(c.getString(COL_CONT_HD));
            mVehHidesEditText.setText(c.getString(COL_VEH_HD));
            mEliteHidesEditText.setText(c.getString(COL_ELITE_HD));

        }

        Button save_edit_button = (Button) rootView.findViewById(R.id.eventEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {

                ContentValues mEditContentValues = new ContentValues();

                String sortOrder = EventEntry._ID + " ASC";
                String selection = EventEntry._ID + " = ?";
                String[] selectionArgs = {Long.valueOf(EventEntry.getEventIdFromUri(mUri)).toString()};

                Cursor cEv = getContext().getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, selection, selectionArgs, sortOrder);

                if(cEv.moveToFirst()) {

                    mEditContentValues.put(EventEntry.COLUMN_NAME, mNameEditText.getText().toString());
                    mEditContentValues.put(EventEntry.COLUMN_LOCATION, mLocationEditText.getText().toString());
                    mEditContentValues.put(EventEntry.COLUMN_DATE, mDateEditText.getText().toString());
                    mEditContentValues.put(EventEntry.COLUMN_HOST, mHostEditText.getText().toString());
                    mEditContentValues.put(EventEntry.COLUMN_STATUS, mStatusEditText.getText().toString());
                    mEditContentValues.put(EventEntry.COLUMN_DIVISION, mDivisionEditText.getText().toString());
                    if(!cEv.getString(COL_INT_SA).equals(mIntSearchAreasEditText.getText().toString())){
                        mEditContentValues.put(EventEntry.COLUMN_INT_SEARCH_AREAS, mIntSearchAreasEditText.getText().toString());
                        Utilities.updateScorecards(getContext(), eventid, mIntSearchAreasEditText.getText().toString(), "Interior");
                    }
                    if(!cEv.getString(COL_EXT_SA).equals(mExtSearchAreasEditText.getText().toString())) {
                        mEditContentValues.put(EventEntry.COLUMN_EXT_SEARCH_AREAS, mExtSearchAreasEditText.getText().toString());
                        Utilities.updateScorecards(getContext(), eventid, mExtSearchAreasEditText.getText().toString(), "Exterior");
                    }
                    if(!cEv.getString(COL_CONT_SA).equals(mContSearchAreasEditText.getText().toString())) {
                        mEditContentValues.put(EventEntry.COLUMN_CONT_SEARCH_AREAS, mContSearchAreasEditText.getText().toString());
                        Utilities.updateScorecards(getContext(), eventid, mContSearchAreasEditText.getText().toString(), "Containers");
                    }
                    if(!cEv.getString(COL_VEH_SA).equals(mVehSearchAreasEditText.getText().toString())) {
                        mEditContentValues.put(EventEntry.COLUMN_VEH_SEARCH_AREAS, mVehSearchAreasEditText.getText().toString());
                        Utilities.updateScorecards(getContext(), eventid, mVehSearchAreasEditText.getText().toString(), "Vehicles");
                    }
                    if(!cEv.getString(COL_ELITE_SA).equals(mEliteSearchAreasEditText.getText().toString())) {
                        mEditContentValues.put(EventEntry.COLUMN_ELITE_SEARCH_AREAS, mEliteSearchAreasEditText.getText().toString());
                        Utilities.updateScorecards(getContext(), eventid, mEliteSearchAreasEditText.getText().toString(), "Elite");
                    }
                    mEditContentValues.put(EventEntry.COLUMN_INT_HIDES, mIntHidesEditText.getText().toString());
                    mEditContentValues.put(EventEntry.COLUMN_EXT_HIDES, mExtHidesEditText.getText().toString());
                    mEditContentValues.put(EventEntry.COLUMN_CONT_HIDES, mContHidesEditText.getText().toString());
                    mEditContentValues.put(EventEntry.COLUMN_VEH_HIDES, mVehHidesEditText.getText().toString());
                    mEditContentValues.put(EventEntry.COLUMN_ELITE_HIDES, mEliteHidesEditText.getText().toString());

                    getContext().getContentResolver().update(EventEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);
                }
            }
        };

        save_edit_button.setOnClickListener(edit_saveOnClickListener);



        Button edit_entrants_button = (Button) rootView.findViewById(R.id.event_edit_entrant);

        View.OnClickListener edit_entrantsOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                ((Callback) getActivity()).onEventEditEntrants(mUri);
            }
        };
        edit_entrants_button.setOnClickListener(edit_entrantsOnClickListener);



        Button edit_users_button = (Button) rootView.findViewById(R.id.event_edit_user);

        View.OnClickListener edit_usersOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                ((Callback) getActivity()).onEventEditUsers(mUri);
            }
        };
        edit_users_button.setOnClickListener(edit_usersOnClickListener);


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
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
//
//        ContentValues mEditContentValues = new ContentValues();
//
//        if (data != null && data.moveToFirst()) {
//            String name = data.getString(COL_NAME);
//            mEditContentValues.put(EventEntry.COLUMN_NAME, name);
//
//            String location = data.getString(COL_LOC);
//            mEditContentValues.put(EventEntry.COLUMN_LOCATION, location);
//
//            String date = data.getString(COL_DATE);
//            mEditContentValues.put(EventEntry.COLUMN_DATE, date);
//
//            String host = data.getString(COL_HOST);
//            mEditContentValues.put(EventEntry.COLUMN_HOST, host);
//
//            String status = data.getString(COL_STATUS);
//            mEditContentValues.put(EventEntry.COLUMN_STATUS, status);
//
//            String division = data.getString(COL_DIV);
//            mEditContentValues.put(EventEntry.COLUMN_DIVISION, division);
//
//            String int_search_areas = data.getString(COL_INT_SA);
//            mEditContentValues.put(EventEntry.COLUMN_INT_SEARCH_AREAS, int_search_areas);
//
//            String ext_search_areas = data.getString(COL_EXT_SA);
//            mEditContentValues.put(EventEntry.COLUMN_EXT_SEARCH_AREAS, ext_search_areas);
//
//            String cont_search_areas = data.getString(COL_CONT_SA);
//            mEditContentValues.put(EventEntry.COLUMN_CONT_SEARCH_AREAS, cont_search_areas);
//
//            String veh_search_areas = data.getString(COL_VEH_SA);
//            mEditContentValues.put(EventEntry.COLUMN_VEH_SEARCH_AREAS, veh_search_areas);
//
//            String elite_search_areas = data.getString(COL_ELITE_SA);
//            mEditContentValues.put(EventEntry.COLUMN_ELITE_SEARCH_AREAS, elite_search_areas);
//
//            String int_hides = data.getString(COL_INT_HD);
//            mEditContentValues.put(EventEntry.COLUMN_INT_HIDES, int_hides);
//
//            String ext_hides = data.getString(COL_EXT_HD);
//            mEditContentValues.put(EventEntry.COLUMN_EXT_HIDES, ext_hides);
//
//            String cont_hides = data.getString(COL_CONT_HD);
//            mEditContentValues.put(EventEntry.COLUMN_CONT_HIDES, cont_hides);
//
//            String veh_hides = data.getString(COL_VEH_HD);
//            mEditContentValues.put(EventEntry.COLUMN_VEH_HIDES, veh_hides);
//
//            String elite_hides = data.getString(COL_ELITE_HD);
//            mEditContentValues.put(EventEntry.COLUMN_ELITE_HIDES, elite_hides);
//
////                    mEditContentValues.put(EventEntry.COLUMN_NAME,NameEditText);
////
//        }
//
//        String selection = EventEntry._ID + " = ?";
//        String[] selectionArgs = {Long.valueOf(EventEntry.getEventIdFromUri(mUri)).toString()};
//        getContext().getContentResolver().update(EventEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader){
//
//    }
}