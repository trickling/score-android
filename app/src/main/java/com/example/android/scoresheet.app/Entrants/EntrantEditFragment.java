package com.example.android.scoresheet.app.Entrants;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract.EntrantEntry;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class EntrantEditFragment extends Fragment {

    public static final String LOG_TAG = EntrantEditFragment.class.getSimpleName();

    private static final String[] ENTRANT_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            EntrantEntry.TABLE_NAME + "." + EntrantEntry._ID,
            EntrantEntry.COLUMN_TEAM_DESC
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_ENTRANT_DESC = 1;

    static final String ENTRANTEDIT_URI = "URI";

    private Uri mUri;
    public Uri editUri;
    private EditText mDescrEditText;
    private String DescrEditText;

    public EntrantEditFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // Add this line in order for this fragment to handle menu events.
////        setHasOptionsMenu(true);
//    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.event_new_fragment, menu);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EntrantEditFragment.ENTRANTEDIT_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_edit_entrant, container, false);

        mDescrEditText = (EditText) rootView.findViewById(R.id.entrantEditText);

        mDescrEditText.setText(EntrantEntry.getEntrantDescFromUri(mUri));

        Button save_edit_button = (Button) rootView.findViewById(R.id.entrantEditSave);

//        Button dbmgr_button = (Button) rootView.findViewById(R.id.event_edit_db_mgr);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrEditText = mDescrEditText.getText().toString();

                if ( !(DescrEditText.equals("")) ) {
                    editUri = EntrantEntry.buildEntrantDescUri(DescrEditText);
                    ContentValues mEditContentValues = new ContentValues();
                    mEditContentValues.put(EntrantEntry.COLUMN_TEAM_DESC, DescrEditText);
                    String selection = EntrantEntry.COLUMN_TEAM_DESC + " = ?";
                    String[] selectionArgs = {EntrantEntry.getEntrantDescFromUri(mUri)};
                    getContext().getContentResolver().update(EntrantEntry.CONTENT_URI, mEditContentValues, selection, selectionArgs);
                }
            }
        };

        save_edit_button.setOnClickListener(edit_saveOnClickListener);

//        View.OnClickListener edit_dbMgrOnClickListener = new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent dbmanager = new Intent(getActivity(), AndroidDatabaseManager.class);
//                startActivity(dbmanager);
//            }
//        };
//
//        dbmgr_button.setOnClickListener(edit_dbMgrOnClickListener);

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
//        getLoaderManager().initLoader(EVENT_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

//    @Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle){
//
//        String sortOrder = ScoreSheetContract.EventEntry.COLUMN_SHORT_DESC + " ASC";
//
//        Uri eventUri = ScoreSheetContract.EventEntry.buildEventUri(ScoreSheetContract.EventEntry.TABLE_NAME);
//
//        return new CursorLoader(getActivity(), eventUri, EVENT_COLUMNS, null, null, sortOrder);
//    }

//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
//        mEventAdapter.swapCursor(data);
//
//        if (mPosition != ListView.INVALID_POSITION) {
//            // If we don't need to restart the loader, and there's a desired position to restore to, do so now.
//            mListView.smoothScrollToPosition(mPosition);
//        }
//    }

//    @Override
//    public void onLoaderReset(Loader<Cursor> loader){  // Lesson 5.14
//        mEventAdapter.swapCursor(null);
//    }

//    private void updateEvents(){
//        ScoreSheetSyncAdapter.syncImmediately(getActivity());
//    }
}
