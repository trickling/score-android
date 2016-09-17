package com.example.android.scoresheet.app;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.scoresheet.app.data.ScoreSheetContract;

/**
 * Created by Kari Stromsland on 9/15/2016.
 */
public class EventEditFragment extends Fragment {

    public static final String LOG_TAG = EventEditFragment.class.getSimpleName();

    private static final String[] EVENT_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            ScoreSheetContract.EventEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntry._ID,
            ScoreSheetContract.EventEntry.COLUMN_SHORT_DESC
    };
    static final int COL_EVENT_ID = 0;
    static final int COL_EVENT_DESC = 1;
    static final String EVENTEDIT_URI = "URI";

    private Uri mUri;
    public Uri editUri;
    private EditText mDescrEditText;
    private String DescrEditText;

    public EventEditFragment() {
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
            mUri = arguments.getParcelable(EventEditFragment.EVENTEDIT_URI);
        }

        View rootView = inflater.inflate(R.layout.fragment_edit_event, container, false);

        mDescrEditText = (EditText) rootView.findViewById(R.id.eventEditText);

        mDescrEditText.setText(ScoreSheetContract.EventEntry.getEventDescriptionFromUri(mUri));

        Button save_edit_button = (Button) rootView.findViewById(R.id.eventEditSave);

        View.OnClickListener edit_saveOnClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                DescrEditText = mDescrEditText.getText().toString();

                if ( !(DescrEditText.equals("")) ) {
                    editUri = ScoreSheetContract.EventEntry.buildEventDesc(DescrEditText);
                    ContentValues mEditContentValues = new ContentValues();
                    mEditContentValues.put(ScoreSheetContract.EventEntry.COLUMN_SHORT_DESC, DescrEditText);
                    String selection = ScoreSheetContract.EventEntry.COLUMN_SHORT_DESC + " = ?";
                    String[] selectionArgs = {ScoreSheetContract.EventEntry.getEventDescriptionFromUri(mUri)};
                    getContext().getContentResolver().update(mUri, mEditContentValues, selection, selectionArgs);
                }
            }
        };

        save_edit_button.setOnClickListener(edit_saveOnClickListener);

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
//        Uri eventUri = ScoreSheetContract.EventEntry.buildEvent(ScoreSheetContract.EventEntry.TABLE_NAME + "." + ScoreSheetContract.EventEntry._ID);
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
