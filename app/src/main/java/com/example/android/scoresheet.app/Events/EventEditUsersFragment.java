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
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            UserEntry.TABLE_NAME + "." + UserEntry._ID,
            UserEntry.COLUMN_USER_DESC
    };
    static final int COL_USER_ID = 0;
    static final int COL_USER_DESC = 1;

    private static final String[] EVENT_COLUMNS = {
            EventEntry.TABLE_NAME + "." + EventEntry._ID,
            EventEntry.COLUMN_SHORT_DESC
    };
    public static final int COL_EVENT_ID = 0;
    public static final int COL_EVENT_DESC = 1;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
//        setHasOptionsMenu(true);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.user_options_fragment, menu);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
////        if (id == R.id.action_map) {
////            openPreferredLocationInMap();
////            return true;
////        }
//        return super.onOptionsItemSelected(item);
//    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = new MenuInflater(getContext());
//        inflater.inflate(R.menu.event_edit_users_fragment, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        mUri = ScoreSheetContract.UserEntry.buildUserDesc(mEventUserListAdapter.getCursor().getString(COL_USER_DESC));
//        switch (item.getItemId()) {
//            case R.id.edit:
//                editEventUser(mUri, info.id);
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }
//
//    private void editEventUser(Uri itemUri, long l){
//        ((Callback) getActivity()).onEventUsersEdit(itemUri);
//    }



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
//                event_edit_checked = mListView.isItemChecked(position);
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

                // Start the CAB using the ActionMode.Callback defined above
                view.setSelected(true);
                return true;
            }
        });


        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
        }


//        SparseBooleanArray lv_ids = mListView.getCheckedItemPositions();
//
//        for(int i=0; i<lv_ids.size(); i++) {
//            if(lv_ids.valueAt(i)){
//                mListView.setItemChecked(i, true);
//            }else{
//                mListView.setItemChecked(i, false);
//            }
//        }

        return rootView;

    }

    public static boolean checked_status(Context context, Cursor c){
        boolean checked = false;
        long userid = c.getLong(0);
        long eventid = EventEntry.getEventIdFromUri(eventUri);
        Uri uri = UserEntry.buildUserEventIdCheckedUri(eventid, "checked");
//        String selection = EventEntry.COLUMN_SHORT_DESC + " = ?";
//        String[] selectionArgs = {EventEntry.getEventDescriptionFromUri(mUri)};
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

        String sortOrder = UserEntry.COLUMN_USER_DESC + " ASC";

        // CursorLoader is a loader that queries the ContentResolver and returns a Cursor.  This class implements
        // the Loader protocol in a standard way for querying cursors, building on AsyncTaskLoader to perform
        // the cursor query on a background thread.
//        return new CursorLoader(getActivity(), UserEntry.CONTENT_URI, USER_COLUMNS, null, null, sortOrder);

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

//    private void updateUsers(){
//        ScoreSheetSyncAdapter.syncImmediately(getActivity());
//    }

}
