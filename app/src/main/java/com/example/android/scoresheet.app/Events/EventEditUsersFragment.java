package com.example.android.scoresheet.app.Events;

import android.content.ContentValues;
import android.content.Context;
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
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventUserEntry;
import com.example.android.scoresheet.app.data.ScoreSheetContract.UserEntry;


/**
 * Created by Kari Stromsland on 10/3/2016.
 */
public class EventEditUsersFragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{
    // A loader is a class that performs asynchronous loading of data.
    // LoaderManager is an interface for managing one or more Loader instances associated with it.
    // LoaderManager.LoaderCallbacks <D> is a callback interface for a client to interact with the manager, D data.

    public static final String LOG_TAG = EventEditUsersFragment.class.getSimpleName();
    private EventEditUsersListAdapter mEventUserListAdapter;
    private ListView mListView;
    private Uri mUri;
    private static Uri eventUri;
    private long eventid;
    private long userid;
    private Boolean event_edit_checked;
    static final String EVENTEDITUSERS_URI = "URI";
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";

    private static final int EVENTUSERS_LOADER = 0;

    private static final String[] USER_COLUMNS = {
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
    static final int COL_STATUS = 5;
    static final int COL_EMAIL = 6;
    static final int COL_PASSWD = 7;

//    private static final String[] EVENT_COLUMNS = {
//            EventEntry.TABLE_NAME + "." + EventEntry._ID,
//            EventEntry.COLUMN_NAME
//    };
//    public static final int COL_EVENT_ID = 0;
//    public static final int COL_EVENT_NAME = 1;

    private static final String[] EVENTUSER_COLUMNS = {
            EventUserEntry.TABLE_NAME + "." + EventUserEntry._ID,
            EventUserEntry.COLUMN_EVENT_ID, EventUserEntry.COLUMN_USER_ID
    };
    public static final int COL_EVENTUSER_ID = 0;
    public static final int COL_EV_ID = 1;
    public static final int COL_US_ID = 2;

    public EventEditUsersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // ListAdapter for a ListView which derives AbsListView which derives from AdapterView.
        // AbsListView includes a RecyclerListener interface
        // The CursorAdapter binds db data to a child view of the listview. An adapter object acts as a bridge
        // between an AdapterView and the underlying data for that view.  The adapter provides access to the
        // data items.  The adapter is also responsible for making a view for each item in the data set.
        mEventUserListAdapter = new EventEditUsersListAdapter(getActivity(), null, 0);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_users_edit_event, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            eventUri = arguments.getParcelable(EventEditUsersFragment.EVENTEDITUSERS_URI);
        }

        mListView = (ListView) rootView.findViewById(R.id.listview_event_edit_users);

        registerForContextMenu(mListView);

        mListView.setAdapter(mEventUserListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // OnClick if false, get unchecked user_id with ""  if true, update user_id with checkmarked user_id
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                CheckedTextView tt = (CheckedTextView) view.findViewById(R.id.list_item_event_edit_users_textview);
                if (!tt.isChecked()){
                    mListView.setItemChecked(position, true);
                    tt.setChecked(true);
                    tt.setCheckMarkDrawable(android.R.drawable.checkbox_on_background);
                    event_edit_checked = true;
                }else{
                    mListView.setItemChecked(position, false);
                    tt.setChecked(false);
                    tt.setCheckMarkDrawable(android.R.drawable.checkbox_off_background);
                    event_edit_checked = false;
                }
                if (cursor != null) {
                    mUri = UserEntry.buildUserIdUri(cursor.getLong(COL_USER_ID));
                    userid = UserEntry.getUserIdFromUri(mUri);
                    eventid = EventEntry.getEventIdFromUri(eventUri);
                    if (event_edit_checked) {
                        ContentValues mEditContentValues = new ContentValues();
                        mEditContentValues.put(EventUserEntry.COLUMN_USER_ID, userid);
                        mEditContentValues.put(EventUserEntry.COLUMN_EVENT_ID, eventid);
                        getContext().getContentResolver().insert(EventUserEntry.buildEventUserUri(), mEditContentValues);
                    }else{
                        String sortOrder = EventUserEntry._ID + " ASC";
                        String selection = EventUserEntry.COLUMN_USER_ID + " = ?" + " AND " + EventUserEntry.COLUMN_EVENT_ID + " = ?";
                        Uri uri = EventUserEntry.buildIdEventUser(Long.valueOf(eventid).toString(), Long.valueOf(userid).toString());
                        String[] selectionArgs = {Long.valueOf(userid).toString(), Long.valueOf(eventid).toString()};
                        Cursor c = getContext().getContentResolver().query(uri, EVENTUSER_COLUMNS, selection, selectionArgs, sortOrder);
                        if(c.moveToFirst()) {
                            long eventuser_id = c.getLong(COL_EVENTUSER_ID);
                            String dSelection = EventUserEntry._ID + " = ?";
                            String[] dSelectionArgs = {Long.valueOf(eventuser_id).toString()};
                            getContext().getContentResolver().delete(EventUserEntry.CONTENT_URI, dSelection, dSelectionArgs);
                        }
                    }
                }
                mPosition = position;
            }
        });

        mListView.setOnLongClickListener(new View.OnLongClickListener() {
            // Called when the user long-clicks on someView

            public boolean onLongClick(View view) {

                view.setSelected(true);
                return true;
            }
        });


        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }

        return rootView;
    }

    public static boolean checked_status(Context context, Cursor c){
        boolean checked = false;
        long userid = c.getLong(0);
        long eventid = EventEntry.getEventIdFromUri(eventUri);
        Uri uri = UserEntry.buildUserEventIdCheckedUri(eventid, "checked");
        Cursor cursor;
        cursor = context.getContentResolver().query(uri, USER_COLUMNS, null, null, null);
        if (!cursor.moveToFirst()) {
            checked = false;
        }else {
            do {
                if(cursor.getLong(0) == userid) {
                    checked = true;
                }
            }while(cursor.moveToNext());
        }
        return checked;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(EVENTUSERS_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // When tablets rotate, the currently selected list item needs to be saved.
        // When no item is selected, mPosition will be set to Listview.INVALID_POSITION,
        // so check for that before storing.
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle){

        String sortOrder = UserEntry.COLUMN_FIRST_NAME + " ASC";

        // CursorLoader is a loader that queries the ContentResolver and returns a Cursor.  This class implements
        // the Loader protocol in a standard way for querying cursors, building on AsyncTaskLoader to perform
        // the cursor query on a background thread.
        return new CursorLoader(getActivity(), UserEntry.CONTENT_URI, USER_COLUMNS, null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        mEventUserListAdapter.swapCursor(data);
        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){  // Lesson 5.14
        mEventUserListAdapter.swapCursor(null);
    }

}