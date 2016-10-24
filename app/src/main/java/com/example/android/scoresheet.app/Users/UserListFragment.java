package com.example.android.scoresheet.app.Users;

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
import com.example.android.scoresheet.app.data.ScoreSheetContract.UserEntry;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class UserListFragment extends Fragment implements LoaderManager.LoaderCallbacks <Cursor>{
    public static final String LOG_TAG = UserListFragment.class.getSimpleName();
    private UserListAdapter mUserListAdapter;
    private ListView mListView;
    private Uri mUri;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";

    private static final int USER_LOADER = 0;

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


    public interface Callback {

        public void onItemSelected(Uri descUri);
        public void onUserEdit(Uri editUri);
    }

    public UserListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(getContext());
        inflater.inflate(R.menu.user_list_fragment, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        mUri = UserEntry.buildUserIdUri(mUserListAdapter.getCursor().getLong(COL_USER_ID));
        switch (item.getItemId()) {
            case R.id.edit:
                editUser(mUri, info.id);
                return true;
            case R.id.delete:
                deleteUser(mUri, info.id);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteUser(Uri itemUri, long l){
        String selection = UserEntry._ID + " = ?";
        String[] selectionArgs = {Long.valueOf(UserEntry.getUserIdFromUri(mUri)).toString()};
        getContext().getContentResolver().delete(UserEntry.CONTENT_URI, selection, selectionArgs);
    }

    private void editUser(Uri itemUri, long l){
        ((Callback) getActivity()).onUserEdit(itemUri);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mUserListAdapter = new UserListAdapter(getActivity(), null, 0);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_user, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listview_users);

        registerForContextMenu(mListView);

        mListView.setAdapter(mUserListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);

                if (cursor != null) {
                    ((Callback) getActivity()).onItemSelected(UserEntry.buildUserIdUri(cursor.getLong(COL_USER_ID)));
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
        getLoaderManager().initLoader(USER_LOADER, null, this);
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
        return new CursorLoader(getActivity(), UserEntry.CONTENT_URI, USER_COLUMNS, null, null, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        mUserListAdapter.swapCursor(data);

        if (mPosition != ListView.INVALID_POSITION) {
            // If we don't need to restart the loader, and there's a desired position to restore to, do so now.
            mListView.smoothScrollToPosition(mPosition);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mUserListAdapter.swapCursor(null);
    }

}