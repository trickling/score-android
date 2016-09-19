package com.example.android.scoresheet.app.Entrants;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.scoresheet.app.R;
import com.example.android.scoresheet.app.data.ScoreSheetContract;

/**
 * Created by Kari Stromsland on 9/18/2016.
 */
public class EntrantViewDetailFragment extends Fragment {

    public static final String LOG_TAG = EntrantViewDetailFragment.class.getSimpleName();

    private static final String[] ENTRANT_COLUMNS = {
            // In this case the id needs to be fully qualified with a table name, since
            // the content provider joins the location & weather tables in the background
            // (both have an _id column)
            // On the one hand, that's annoying.  On the other, you can search the weather table
            // using the location set by the user, which is only in the Location table.
            // So the convenience is worth it.
            ScoreSheetContract.EntrantEntry.TABLE_NAME + "." + ScoreSheetContract.EntrantEntry._ID,
            ScoreSheetContract.EntrantEntry.COLUMN_TEAM_DESC
    };
    static final int COL_ENTRANT_ID = 0;
    static final int COL_ENTRANT_DESC = 1;
    static final String ENTRANTVIEW_URI = "URI";

    private Uri mUri;
    private TextView mDescrViewText;

    public EntrantViewDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
//        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.entrant_view_detail_fragment, menu);
    }

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

        View rootView = inflater.inflate(R.layout.fragment_detail_view_entrant, container, false);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(EntrantViewDetailFragment.ENTRANTVIEW_URI);
        }

        mDescrViewText = (TextView) rootView.findViewById(R.id.entrant_text_view);

        mDescrViewText.setText(ScoreSheetContract.EntrantEntry.getEntrantDescriptionFromUri(mUri));

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
