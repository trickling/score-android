package com.example.android.scoresheet.app.Events;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.Users.UserListAdapter;
import com.example.android.scoresheet.app.data.ScoreSheetContract.UserEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventUserEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;

/**
 * Created by Kari Stromsland on 9/9/2016.
 */
public class EventViewDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{

    private static final String LOG_TAG = EventViewDetailFragment.class.getSimpleName();
    private UserListAdapter mEventUserListAdapter;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    static final String EVENTVIEW_URI = "URI";
    private static final String EVENT_SHARE_HASHTAG = " #ScoreSheetApp";
    private Uri mUri;
    private Uri eventUri;
    private static final int EVENT_LOADER = 0;
    private static final int EVENT_USER_LOADER = 1;

    private static final String[] EVENT_COLUMNS = {
            EventEntry.TABLE_NAME + "." + EventEntry._ID,
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

    private static final String[] USER_DETAIL_COLUMNS = {
            UserEntry.TABLE_NAME + "." + UserEntry._ID,
            UserEntry.COLUMN_FIRST_NAME,
            UserEntry.COLUMN_LAST_NAME,
            UserEntry.COLUMN_ROLE,
            UserEntry.COLUMN_APPROVED,
            UserEntry.COLUMN_STATUS,
            UserEntry.COLUMN_EMAIL,
            UserEntry.COLUMN_PASSWORD
    };
    static final int COL_USER_ID = 0;
    static final int COL_FIRST_NAME = 1;
    static final int COL_LAST_NAME = 2;
    static final int COL_ROLE = 3;
    static final int COL_APPROVED = 4;
    static final int COL_USRSTATUS = 5;
    static final int COL_EMAIL = 6;
    static final int COL_PASSWD = 7;

    private static final String[] EVENTUSER_COLUMNS = {
            EventUserEntry.TABLE_NAME + "." + EventUserEntry._ID,
            EventUserEntry.COLUMN_EVENT_ID, EventUserEntry.COLUMN_USER_ID
    };

    public static final int COL_EVENTUSER_ID = 0;
    public static final int COL_EV_ID = 1;
    public static final int COL_US_ID = 2;

    private TextView mNameViewText;
    private TextView mLocationViewText;
    private TextView mDateViewText;
    private TextView mHostViewText;
    private TextView mStatusViewText;
    private TextView mDivisionViewText;
    private TextView mIntSearchAreasViewText;
    private TextView mExtSearchAreasViewText;
    private TextView mContSearchAreasViewText;
    private TextView mVehSearchAreasViewText;
    private TextView mEliteSearchAreasViewText;
    private TextView mIntHidesViewText;
    private TextView mExtHidesViewText;
    private TextView mContHidesViewText;
    private TextView mVehHidesViewText;
    private TextView mEliteHidesViewText;

    public EventViewDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mEventUserListAdapter = new UserListAdapter(getActivity(), null, 0);

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_detail_view_event, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EventViewDetailFragment.EVENTVIEW_URI);
        }

        mListView = (ListView) rootView.findViewById(R.id.listview_user_events);

        mListView.setAdapter(mEventUserListAdapter);

        mNameViewText = (TextView) rootView.findViewById(R.id.event_name_text_view);
        mLocationViewText = (TextView) rootView.findViewById(R.id.event_loc_text_view);
        mDateViewText = (TextView) rootView.findViewById(R.id.event_date_text_view);
        mHostViewText = (TextView) rootView.findViewById(R.id.event_host_text_view);
        mStatusViewText = (TextView) rootView.findViewById(R.id.event_status_text_view);
        mDivisionViewText = (TextView) rootView.findViewById(R.id.event_division_text_view);
        mIntSearchAreasViewText = (TextView) rootView.findViewById(R.id.event_int_sa_text_view);
        mExtSearchAreasViewText = (TextView) rootView.findViewById(R.id.event_ext_sa_text_view);
        mContSearchAreasViewText = (TextView) rootView.findViewById(R.id.event_cont_sa_text_view);
        mVehSearchAreasViewText = (TextView) rootView.findViewById(R.id.event_veh_sa_text_view);
        mEliteSearchAreasViewText = (TextView) rootView.findViewById(R.id.event_elite_sa_text_view);
        mIntHidesViewText = (TextView) rootView.findViewById(R.id.event_int_hd_text_view);
        mExtHidesViewText = (TextView) rootView.findViewById(R.id.event_ext_hd_text_view);
        mContHidesViewText = (TextView) rootView.findViewById(R.id.event_cont_hd_text_view);
        mVehHidesViewText = (TextView) rootView.findViewById(R.id.event_veh_hd_text_view);
        mEliteHidesViewText = (TextView) rootView.findViewById(R.id.event_elite_hd_text_view);

        String sortOrder = EventEntry._ID + " ASC";
        String selection = EventEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(EventEntry.getEventIdFromUri(mUri)).toString()};

        Cursor c = getContext().getContentResolver().query(EventEntry.CONTENT_URI, EVENT_COLUMNS, selection, selectionArgs, sortOrder);

        if(c.moveToFirst()) {
            mNameViewText.setText(c.getString(COL_NAME));
            mLocationViewText.setText(c.getString(COL_LOC));
            mDateViewText.setText(c.getString(COL_DATE));
            mHostViewText.setText(c.getString(COL_HOST));
            mStatusViewText.setText(c.getString(COL_STATUS));
            mDivisionViewText.setText(c.getString(COL_DIV));
            mIntSearchAreasViewText.setText(c.getString(COL_INT_SA));
            mExtSearchAreasViewText.setText(c.getString(COL_EXT_SA));
            mContSearchAreasViewText.setText(c.getString(COL_CONT_SA));
            mVehSearchAreasViewText.setText(c.getString(COL_VEH_SA));
            mEliteSearchAreasViewText.setText(c.getString(COL_ELITE_SA));
            mIntHidesViewText.setText(c.getString(COL_INT_HD));
            mExtHidesViewText.setText(c.getString(COL_EXT_HD));
            mContHidesViewText.setText(c.getString(COL_CONT_HD));
            mVehHidesViewText.setText(c.getString(COL_VEH_HD));
            mEliteHidesViewText.setText(c.getString(COL_ELITE_HD));
        }

        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(EVENT_USER_LOADER, null, this);
//        getLoaderManager().initLoader(EVENT_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle){

//        if (i == EVENT_LOADER && null != mUri){
//
//            return new CursorLoader(getActivity(), eventUri, EVENT_COLUMNS, null, null, null);
//
//        }else if (i == EVENT_USER_LOADER) {
            String sortOrder = UserEntry.COLUMN_FIRST_NAME + " ASC";

            Uri event_userUri = UserEntry.buildUserIdUri(EventEntry.getEventIdFromUri(mUri));

            return new CursorLoader(getActivity(), event_userUri, USER_DETAIL_COLUMNS, null, null, sortOrder);
//        }
//        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){

        mEventUserListAdapter.swapCursor(data);

        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }

//        if (data != null && data.moveToFirst()) {
//
//            String name = data.getString(COL_NAME);
//            mNameViewText.setText(name);
//            String location = data.getString(COL_LOC);
//            mLocationViewText.setText(location);
//            String date = data.getString(COL_DATE);
//            mDateViewText.setText(date);
//            String host = data.getString(COL_HOST);
//            mHostViewText.setText(host);
//            String status = data.getString(COL_STATUS);
//            mStatusViewText.setText(status);
//            String division = data.getString(COL_DIV);
//            mDivisionViewText.setText(division);
//            String int_search_areas = data.getString(COL_INT_SA);
//            mIntSearchAreasViewText.setText(int_search_areas);
//            String ext_search_areas = data.getString(COL_EXT_SA);
//            mExtSearchAreasViewText.setText(ext_search_areas);
//            String cont_search_areas = data.getString(COL_CONT_SA);
//            mContSearchAreasViewText.setText(cont_search_areas);
//            String veh_search_areas = data.getString(COL_VEH_SA);
//            mVehSearchAreasViewText.setText(veh_search_areas);
//            String elite_search_areas = data.getString(COL_ELITE_SA);
//            mEliteSearchAreasViewText.setText(elite_search_areas);
//            String int_hides = data.getString(COL_INT_HD);
//            mIntHidesViewText.setText(int_hides);
//            String ext_hides = data.getString(COL_EXT_HD);
//            mExtHidesViewText.setText(ext_hides);
//            String cont_hides = data.getString(COL_CONT_HD);
//            mContHidesViewText.setText(cont_hides);
//            String veh_hides = data.getString(COL_VEH_HD);
//            mVehHidesViewText.setText(veh_hides);
//            String elite_hides = data.getString(COL_ELITE_HD);
//            mEliteHidesViewText.setText(elite_hides);
//        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mEventUserListAdapter.swapCursor(null);
    }

}
