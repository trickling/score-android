package com.example.android.scoresheet.app.Events;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EventEntry;

/**
 * Created by Kari Stromsland on 9/6/2016.
 */
public class EventListFragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{
    public static final String LOG_TAG = EventListFragment.class.getSimpleName();
    private EventListAdapter mEventListAdapter;
    private ListView mListView;
    private Uri mUri;
    private String DescrText = new String("");
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";

    private static final int EVENT_LOADER = 0;

    private static final String[] EVENT_COLUMNS = {
            EventEntry.TABLE_NAME + "." + EventEntry._ID,
            EventEntry.COLUMN_SHORT_DESC
    };
    static final int COL_EVENT_ID = 0;
    static final int COL_EVENT_DESC = 1;


    public interface Callback {

        public void onItemSelected(Uri descUri);
        public void onEventEdit(Uri editUri);
    }

    public EventListFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(getContext());
        inflater.inflate(R.menu.event_list_fragment, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        mUri = EventEntry.buildEventIdDescUri(mEventListAdapter.getCursor().getLong(COL_EVENT_ID), mEventListAdapter.getCursor().getString(COL_EVENT_DESC));
        switch (item.getItemId()) {
            case R.id.edit:
                editEvent(mUri, info.id);
                return true;
            case R.id.delete:
                deleteEvent(mUri, info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteEvent(Uri itemUri, long l){
        String selection = EventEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf((EventEntry.getEventIdFromUri(mUri))).toString()};
        getContext().getContentResolver().delete(EventEntry.CONTENT_URI, selection, selectionArgs);
    }

    private void editEvent(Uri itemUri, long l){
        ((Callback) getActivity()).onEventEdit(itemUri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mEventListAdapter = new EventListAdapter(getActivity(), null, 0);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_event, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listview_events);

        registerForContextMenu(mListView);

        mListView.setAdapter(mEventListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);

                if (cursor != null) {
                    ((Callback) getActivity()).onItemSelected(EventEntry.buildEventIdDescUri(mEventListAdapter.getCursor().getLong(COL_EVENT_ID), mEventListAdapter.getCursor().getString(COL_EVENT_DESC)));
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(EVENT_LOADER, null, this);
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

        String sortOrder = EventEntry.COLUMN_SHORT_DESC + " ASC";
        return new CursorLoader(getActivity(), EventEntry.CONTENT_URI, EVENT_COLUMNS, null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        mEventListAdapter.swapCursor(data);

        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){  // Lesson 5.14
        mEventListAdapter.swapCursor(null);
    }
}